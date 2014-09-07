package com.music.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.InitializingBean;

public class MusicRecommendation implements InitializingBean {

	private ItemBasedRecommender recommender;

	private static boolean initialized = false;

	private final static MusicRecommendation instance = new MusicRecommendation();

	private MusicRecommendation() {

		System.out.println("MusicRecommendation called");
		
		if (!initialized) {
			initialized = true;
			long start = System.currentTimeMillis();

			DataModel model;
			try {
				model = new FileDataModel(new File("/Users/Abinaya/Desktop/Dataset/test_0.txt"));
				ItemSimilarity userSimilarity = new LogLikelihoodSimilarity(model);
				UserSimilarity similarity= new LogLikelihoodSimilarity(model);

				recommender = new GenericItemBasedRecommender(model, userSimilarity);
				 NearestNUserNeighborhood neighborhood =new NearestNUserNeighborhood(200, similarity, model);
				System.out.println("Time taken to startup : " + (System.currentTimeMillis() - start));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TasteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void afterPropertiesSet() {
	}

	public String itemrecommend(long itemID) {
		List<RecommendedItem> recommendations = null;
		StringBuilder sb = new StringBuilder();
		try {

			long start = System.currentTimeMillis();
			recommendations = instance.recommender.mostSimilarItems(itemID, 5);
			System.out.println("Time taken to recommend : " + (System.currentTimeMillis() - start));


			sb.append("[");
			String delimiter = "";
			
			for (RecommendedItem recommendation : recommendations) {
				sb.append(delimiter);
				sb.append(recommendation.getItemID());
				delimiter=", ";
			}
			sb.append("]");

			System.out.println("All done!!");
		} catch (TasteException e) {
			sb.append("Oops!!! Sorry we do not have this song.. Please Choose another song");
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String userRecommend(long userId) {
		StringBuilder sb= new StringBuilder();
		Recommender cachingRecommender;
		try {
			cachingRecommender = new CachingRecommender(recommender);
		
	    List<RecommendedItem> recommendations =cachingRecommender.recommend(userId, 3);
	    sb.append("[");
		String delimiter = "";
		
		for (RecommendedItem recommendation : recommendations) {
			sb.append(delimiter);
			sb.append(recommendation.getItemID());
			delimiter=", ";
		}
		sb.append("]");
		} catch (TasteException e) {
			sb.append("Invalid User...Please enter the correct user id");
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static MusicRecommendation getInstance() {
		return instance;
	}
}
