package com.example.demo.officemodel;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name="userid")
	 private String userid;
	
    @Column(name="photos")
    private String photos;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || userid == null) return null;
         
        return "/user-photos/" + id + "/" + photos;
    }
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
    
    
    

}
