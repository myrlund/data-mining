package no.ntnu.idi.dm.arm.apriori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BruteForceApriori<V> extends BaseApriori<V> {

	public BruteForceApriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public void apriori(Double minSupport) {
		Map<Integer, List<ItemSet<V>>> allItemSets = getAllItemSets();
		
		int total = 0;
		int totalFrequent = 0;
		for (Entry<Integer, List<ItemSet<V>>> entry : allItemSets.entrySet()) {
			total += entry.getValue().size();
			
			List<ItemSet<V>> addedItemSets = new ArrayList<ItemSet<V>>();
			for (ItemSet<V> itemSet : entry.getValue()) {
				int n = 0;
				for (ItemSet<V> transaction : transactions) {
					if (transaction.intersection(itemSet).size() == itemSet.size()) {
						n++;
					}
				}
				
				if ((float)n / transactions.size() >= minSupport) {
					System.out.println("Adding "+itemSet+" to level "+entry.getKey());
					addedItemSets.add(itemSet);
				}
			}
			frequentItemSets.put(entry.getKey(), addedItemSets);
			totalFrequent += addedItemSets.size();
		}
		
		System.out.println("Number of candidates: " + total);
		System.out.println("Number of pruned item sets: " + (total - totalFrequent));
	}
	
	private Map<Integer, List<ItemSet<V>>> getAllItemSets() {
		Map<Integer, List<ItemSet<V>>> allItemSets = new HashMap<Integer, List<ItemSet<V>>>();
		
		allItemSets.put(new Integer(1), new ArrayList<ItemSet<V>>());
		for (ItemSet<V> is : getAllItemsetsOfSizeOne()) {
			allItemSets.get(new Integer(1)).add(is);
		}
		
		Integer level = 2;
		while (allItemSets.get(level - 1).size() > 0) {
			allItemSets.put(new Integer(level), new ArrayList<ItemSet<V>>());
			
			for (ItemSet<V> baseItemSet : getAllItemsetsOfSizeOne()) {
				for (ItemSet<V> previousItemSet : allItemSets.get(new Integer(level - 1))) {
					ItemSet<V> candidateItemSet = baseItemSet.union(previousItemSet);
					if (candidateItemSet.size() == level && !allItemSets.get(new Integer(level)).contains(candidateItemSet)) {
						allItemSets.get(new Integer(level)).add(candidateItemSet);
					}
				}
			}
			
			level++;
		}
		
		return allItemSets;
	}
	
}
