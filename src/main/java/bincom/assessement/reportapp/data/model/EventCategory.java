package bincom.assessement.reportapp.data.model;

public enum EventCategory {
 FIGHT("fight"), RIOT("riot"), ACCIDENT("accident"), RALLY("rally"), PROTEST("protest");

    private String category;
 EventCategory(String category){
     this.category = category;
 }

}
