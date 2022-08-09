package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Database.Connection.DBEvent;
import Entity.CharityEvent;
import Entity.CharityEventRecommendation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RetrieveDisplay {
	private DBEvent db_function  = new DBEvent();
	private final int RECOMMEND_AMOUNT = 6;
	
	public ObservableList<ImageView> pictureForFundRaise(CharityEvent eventPicture){
		List<String> fundRaisePicture = new ArrayList<String>();
		try {
			fundRaisePicture = eventPicture.getImage();
			
		} catch (Exception e1) {
			System.out.println("File is not found");
			e1.printStackTrace();
		}
		
		ObservableList<ImageView> pictureList = FXCollections.observableArrayList();
		
		ImageView view;
		try {
			//Front
			for(int i = 0 ; i < fundRaisePicture.size() ; i++){
				Image pic = new Image(new FileInputStream(fundRaisePicture.get(i)));
				view = new ImageView(pic);
				view.setPreserveRatio(false);
				view.setFitHeight(133);
				view.setFitWidth(143);
				view.setAccessibleText(fundRaisePicture.get(i));
				view.setId("img");
				
				pictureList.add(view);
			}

		} catch (FileNotFoundException e) {
			System.err.println("Error in retrieveDisplayComponent ... fundRaisePicture");
			e.printStackTrace();
		}
		return pictureList;
	}
	
	public ObservableList<ImageView> pictureForRecommendation(CharityEventRecommendation eventRecommend){
		List<String> recommendPicture = new ArrayList<String>();
		try {
			recommendPicture = eventRecommend.getImage();
			
			
		} catch (Exception e1) {
			System.out.println("pictureForRecommendation :: File is not found");
			e1.printStackTrace();
		}
		
		ObservableList<ImageView> recommendList = FXCollections.observableArrayList();
		
		ImageView view;
		try {
			//Front
			for(int i = 0 ; i < RECOMMEND_AMOUNT ; i++){
				Image pic = new Image(new FileInputStream(recommendPicture.get(i)));
				view = new ImageView(pic);
				view.setPreserveRatio(false);
				view.setFitHeight(133);
				view.setFitWidth(143);
				view.setAccessibleText(recommendPicture.get(i));
				view.setId("img");
				recommendList.add(view);
			}

		} catch (FileNotFoundException e) {
			System.err.println("pictureForRecommendation :: FILE NOT FOUND YET 2");
			e.printStackTrace();
		}
		return recommendList;
	}
}
