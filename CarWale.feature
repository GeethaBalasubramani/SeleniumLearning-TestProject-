Feature: Car Wale;

Scenario: To Find Car with less KM usage

Given User opens the Chrome browser 
And User launches the CarWale application 
And user maximise the browser
And user set implicity wait  
And user Click on UsedTab
And user Select the City as Chennai
And user Select budget min (8L) and max(12L) and Click Search
And user Select Cars with Photos under Only Show Cars With
And user Select Manufacturer as "Hyundai" --> Creta
And user Select Fuel Type as Petrol
And user Select Best Match as "KM: Low to High"
And user Validate the Cars are listed with KMs Low to High and added the least KM car to WishList
And Go to Wishlist and Click on More Details
When Print all the details under Overview in the Same way as displayed in application 
Then Close the browser.