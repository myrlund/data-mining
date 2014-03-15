package no.ntnu.idi.dm.arm.apriori;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FkMinus1FKMinus1<V> extends BaseApriori<V> {

	public FkMinus1FKMinus1(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public List<ItemSet<V>> aprioriGen(
			List<ItemSet<V>> frequentCandidatesKMinus1) {

		Collections.sort(frequentCandidatesKMinus1);
		
		Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
		
		for (ItemSet<V> a : frequentCandidatesKMinus1) {
			for (ItemSet<V> b : frequentCandidatesKMinus1) {
				for (V v : b.getItems()) {
					if (!a.getItems().contains(v)) {
						ItemSet<V> unionSet = a.union(v);
						frequentCandidateSet.add(unionSet);
						getAndCacheSupportForItemset(unionSet);
					}
				}
			}
		}

		return new LinkedList<ItemSet<V>>(frequentCandidateSet);
	}

}
