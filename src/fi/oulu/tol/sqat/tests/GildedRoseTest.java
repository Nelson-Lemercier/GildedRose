package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_13() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(13, itemBrie.getQuality()); // Bug here : the rule says that after 5 days left, the Aged Brie wins 3 points of quality
	}
    
	@Test
	public void testUpdateEndOfDay_All_items_have_a_SellIn_value_which_denotes_the_number_of_days_we_have_to_sell_the_item() {
		
		//Arrange
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test Item", 2, 10));
		
		//Assert
		
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals("Items don't have a correct SellIn value", 2, testItem.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_All_items_have_a_Quality_value_which_denotes_how_valuable_the_item_is() {
		
		//Arrange
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test Item", 2, 10));
		
		//Assert
		
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals("Items don't have an Quality value", 10, testItem.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_At_the_end_of_each_day_the_system_lowers_SellIn_values_for_every_item() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test item", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(1, testItem.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_At_the_end_of_each_day_the_system_lowers_Quality_values_for_every_item() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test item", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(9, testItem.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Once_the_sell_by_date_has_passed_Quality_degrades_twice_as_fast() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test item", 0, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(8, testItem.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_The_Quality_of_an_item_is_never_negative() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test item", 2, 0) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(0, testItem.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_12() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 10, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(12, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 20, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_The_Quality_of_an_item_is_never_more_than_50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Test item", 2, 55) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(50, testItem.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_50_50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 30, 50) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_never_has_to_be_sold() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 5, 80) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(5, testItem.getSellIn());
		
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_dont_decreased_in_value() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 5, 80) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(80, testItem.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_Backstage_pass_Quality_0_concert_over() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(0, testItem.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_Quality_not_over_80() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 85));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item testItem = items.get(0);
		assertEquals(80, testItem.getQuality());
		
	}
	
	
	
}
