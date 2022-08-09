package Controller;

import java.util.List;
import org.controlsfx.control.MasterDetailPane;

import Database.Connection.DBEvent;
import Entity.CharityEvent;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SlidePictureMainDisplay {
	
	//Clipboard
	private ClipboardContent cb = new ClipboardContent();
	
	//Classes
	private DBEvent db_function  = new DBEvent();
	 
	private static final Double HALF_PIE = Math.PI / 2;

    // ====================== 2. Instance Fields =============================

    private SimpleDoubleProperty angle = new SimpleDoubleProperty(HALF_PIE);

    private PerspectiveTransform transform = new PerspectiveTransform();

    private SimpleBooleanProperty flippedProperty = new SimpleBooleanProperty(true);
    
    public ObservableList<StackPane> addFlipPane(ObservableList<ImageView> imageList,CharityEvent eventEntity)
{
    	ObservableList<StackPane> stacks= FXCollections.observableArrayList();
    	ObservableList<StackPane> pictureStack = FXCollections.observableArrayList(createFrontListNode(imageList));

    	for(int i=0;i<imageList.size();i++){
    		StackPane stack = new StackPane();
    		stack.getChildren().setAll(addMasterDetail(pictureStack.get(i),createBackListNode(eventEntity).get(i)));

    		recalculateTransformation(angle.doubleValue(),stack);

    		stack.widthProperty().addListener(new ChangeListener<Number>() {

    			@Override public void changed(final ObservableValue<? extends Number> arg0, final Number arg1, final Number arg2)
    			{
    				recalculateTransformation(angle.doubleValue(),stack);
    			}
    		});

    		stack.heightProperty().addListener(new ChangeListener<Number>() {

    			@Override public void changed(final ObservableValue<? extends Number> arg0, final Number arg1, final Number arg2)
    			{
    				recalculateTransformation(angle.doubleValue(),stack);
    			}
    		});
	
    		stacks.add(stack);
    		}
    		angle = createAngleProperty(stacks.get(0));
    		return stacks;
			}	

    private MasterDetailPane addMasterDetail(StackPane picture, GridPane grid) {
    	MasterDetailPane masterdetail = new MasterDetailPane();
    	
		masterdetail.setMasterNode(picture);
		
		masterdetail.setDetailNode(grid);
		masterdetail.setDetailSide(Side.RIGHT);
		masterdetail.setShowDetailNode(false);
		masterdetail.setDividerPosition(0.0);
		masterdetail.setMaxSize(500, 200);
		
		/*
		 * Table Enter and exit events
		 * 
		 */
		picture.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					//flip.flip();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					masterdetail.setShowDetailNode(true);			
				}
		});

		grid.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//flip.flip();
				//System.out.println("pro_sheet Enter");
				masterdetail.setShowDetailNode(false);
		
			}
		});

		return masterdetail;
	}

	public SimpleDoubleProperty createAngleProperty(StackPane flipStack)
    	{
    	// --------------------- <Angle> -----------------------

    	final SimpleDoubleProperty angle = new SimpleDoubleProperty(HALF_PIE);

    		angle.addListener(new ChangeListener<Number>() {

    			@Override public void changed(final ObservableValue<? extends Number> obsValue, final Number oldValue, final Number newValue)
    			{

    				recalculateTransformation(newValue.doubleValue(),flipStack);
    			}
    		});

    		return angle;
    	}

    public void recalculateTransformation(final double angle,StackPane flipStack)
    {
    	final double insetsTop = flipStack.getInsets().getTop() * 2;
    	final double insetsLeft = flipStack.getInsets().getLeft() * 2;


    	final double radius = flipStack.widthProperty().subtract(insetsTop).divide(2).doubleValue();
    	final double height = flipStack.heightProperty().subtract(insetsLeft).doubleValue();
    	final double back = height / 10;

/*
* Compute transform.
* 
* Don't bother understanding these unless you're a math passionate.
* 
* You may Google "Affine Transformation - Rotation"
*/
    	transform.setUlx(radius - Math.sin(angle) * radius);
    	transform.setUly(0 - Math.cos(angle) * back);
    	transform.setUrx(radius + Math.sin(angle) * radius);
    	transform.setUry(0 + Math.cos(angle) * back);
    	transform.setLrx(radius + Math.sin(angle) * radius);
    	transform.setLry(height - Math.cos(angle) * back);
    	transform.setLlx(radius - Math.sin(angle) * radius);
    	transform.setLly(height + Math.cos(angle) * back);
    }

    public ObservableList<StackPane> createFrontListNode(ObservableList<ImageView> list){
    	ObservableList<StackPane> stacks = FXCollections.observableArrayList();

    	for(int i = 0;i<list.size();i++){
    		StackPane node = new StackPane();
    		list.get(i).setFitWidth(900);
    		list.get(i).setFitHeight(600);
    		list.get(i).maxWidth(900);
    		list.get(i).maxHeight(750);
    		
    		node.setEffect(transform);
    		node.visibleProperty().bind(flippedProperty);
    		node.getChildren().setAll(list.get(i));
    		node.setAccessibleText(String.valueOf(i));
//    		System.out.println(node.getAccessibleText());
    		stacks.add(node);
    	}

    	return stacks;	       
    }

    public ObservableList<GridPane> createBackListNode(CharityEvent eventEntity)
    {
    	ObservableList<GridPane> gridList = FXCollections.observableArrayList();
    	List<String> pictureFile = db_function.retrieveDescription().getImage();
    	
    	for(int i = 0;i < pictureFile.size(); i++){
    		GridPane grid = new GridPane();
    		grid.setPrefSize(900, 600);
    		grid.setMaxSize(1050, 750);
	
    		VBox title_organ = new VBox();
    		title_organ.setAlignment(Pos.TOP_CENTER);
    		
    		Label eventName = new Label(eventEntity.getName().get(i));	// name of event
    		eventName.setFont(Font.font ("Arial Rounded MT Bold", 17.5));
    		eventName.setWrapText(true);
    		eventName.setTextAlignment(TextAlignment.CENTER);
    		eventName.setAlignment(Pos.CENTER);
    		eventName.setTextFill(Color.WHITE);
    		eventName.prefWidth(title_organ.getMaxWidth());
    		eventName.prefHeight(80);
    		
    		Label category = new Label(eventEntity.getCategory().get(i));	// category
    		category.setPrefSize(307, 22);
    		category.setFont(Font.font("Arial", 14));
    		category.setStyle("-fx-font-style : italic");
    		category.setTextFill(Color.WHITE);
    		category.setAlignment(Pos.CENTER);
    		Label organization = new Label(eventEntity.getOrganization().get(i));	// category
    		organization.setPrefSize(307, 22);
    		organization.setFont(Font.font("Arial", 14));
    		organization.setStyle("-fx-font-weight : bold");
    		organization.setTextFill(Color.WHITE);
    		organization.setAlignment(Pos.CENTER);
    		title_organ.setPrefSize(600, 90);
    		title_organ.getChildren().addAll(eventName, category, organization);
    		//eventEnity.getCategory().get(i)
    		
    		grid.add(title_organ, 0, 0);
    		
    		GridPane details = new GridPane();
    		
    		GridPane end_start = new GridPane();
    		Label start = new Label(eventEntity.getStart().get(i));
    		start.setId("flip_detail");
    		start.setPrefSize(120, 25);
    		start.setAlignment(Pos.CENTER);
    		end_start.add(start, 0, 0);
    		
    		Label end = new Label(eventEntity.getEnd().get(i));
    		end.setId("flip_detail");
    		start.setPrefSize(120, 25);
    		end.setAlignment(Pos.CENTER);
    		
    		end_start.add(end, 1, 0);
    		end_start.setPrefWidth(317);
    		details.add(end_start, 1, 0, 2, 1);
    		
    		Button moreDetail = new Button("View More");
    		moreDetail.setPrefSize(307, 50);
    		moreDetail.setId("flip_detail");
    		moreDetail.setFont(Font.font("Arial", 15.5));
    		moreDetail.setStyle("-fx-font-weight : bold");
    		moreDetail.setAlignment(Pos.CENTER);
    		moreDetail.setStyle("-fx-background-color :  #feab70");
    		details.add(moreDetail, 1, 2);
    		
    		Label description = new Label(eventEntity.getDescription().get(i));
    		description.setWrapText(true);
    		description.setPrefSize(307, 95);
    		description.setId("flip_detail");
    		description.setAlignment(Pos.CENTER);
    		details.add(description, 1, 1);
    		
    		grid.add(details, 0, 1, 2, 3);;
    		grid.setStyle("-fx-background-color :  #9f70fe");
	    	gridList.add(grid);
    		int index = i;
	    	grid.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
    	    	@Override
    	        public void handle(MouseEvent mouseDrag) {
    	    		grid.setOnDragDetected(new EventHandler<Event>() {
    					@Override
    					public void handle(Event event) {
    						String picture = pictureFile.get(index);
    						
    						Dragboard db = null;
    						try {
    							db = grid.startDragAndDrop(TransferMode.ANY);
    								
    						} catch (Exception e) {
    								e.printStackTrace();
    						}
    	
    			    	    cb.putString(picture);    	
    			    	    db.setContent(cb);
    			    	    event.consume();
    					}
    	 			});
    	    	}
    	    });
    	} 

    	return gridList;
    }
}
