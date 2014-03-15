package no.ntnu.idi.dm.arm.apriori;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FKMinus1F1Apriori<V> extends BaseApriori<V> {

	public FKMinus1F1Apriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public List<ItemSet<V>> aprioriGen(List<ItemSet<V>> frequentCandidatesKMinus1) {
		Collections.sort(frequentCandidatesKMinus1);
		
		List<ItemSet<V>> frequentCandidateSet = new LinkedList<ItemSet<V>>(); 
		
		for (ItemSet<V> baseItemSet : getAllItemsetsOfSizeOne()) {
			for (ItemSet<V> itemSet : frequentCandidatesKMinus1) {
				ItemSet<V> unionSet = baseItemSet.union(itemSet);
				if (unionSet.size() > itemSet.size() && !frequentCandidateSet.contains(unionSet)) {
					frequentCandidateSet.add(unionSet);
					getAndCacheSupportForItemset(unionSet);
				}
			}
		}
		
		return frequentCandidateSet;
	}

}
