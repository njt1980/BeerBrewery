package com.nimel.mymicroservices.beerservice.bootstrap;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nimel.mymicroservices.beerservice.entities.Beer;
import com.nimel.mymicroservices.beerservice.repository.BeerRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BeerBootstrap implements CommandLineRunner{
	
	public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
//    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
//    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
//    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");
    
    @Autowired
    private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		
		loadBeerData();
		// TODO Auto-generated method stub
		
	}

	private void loadBeerData() {
		// TODO Auto-generated method stub
		if (beerRepository.count() == 0) {
			loadInitialBeerData();
		}
		
	}

	private void loadInitialBeerData() {
		// TODO Auto-generated method stub
		Beer b1 = Beer
				.builder()
				.beerName("Beer1")
				.beerStyle("Style1")
				.minOnHand(12)
				.price(2)
				.quantityToBrew(50)
				.upc(BEER_1_UPC)
//				.id(BEER_1_UUID)
				.build();
		
		Beer b2 = Beer
				.builder()
				.beerName("Beer2")
				.beerStyle("Style2")
				.minOnHand(12)
				.price(2)
				.quantityToBrew(50)
				.upc(BEER_2_UPC)
//				.id(BEER_2_UUID)
				.build();
		Beer b3 = Beer
				.builder()
				.beerName("Beer3")
				.beerStyle("Style3")
				.minOnHand(12)
				.price(2)
				.quantityToBrew(50)
				.upc(BEER_3_UPC)
//				.id(BEER_3_UUID)
				.build();
		
		beerRepository.save(b1);
		beerRepository.save(b2);
		beerRepository.save(b3);
		
				
				
	}
    
    

}
