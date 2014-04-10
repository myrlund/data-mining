package no.ntnu.idi.dm.runnables;

import no.ntnu.idi.dm.clustering.KMeans;
import no.ntnu.idi.dm.clustering.VectorTools;
import cern.colt.Arrays;

public class KMeansClustererMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String fileNames[] = new String[] { "iris.arff",
				"segment-challenge.arff" };

		// load data set (in a way that just works)
		for (String fileName : fileNames) {

            double[][] data = VectorTools.readArffFile(fileName);

            System.out.println("\\section{Input file: " + fileName + "}");

            for (int k = 2; k <= 8; k++) {

                System.out.println("\\subsection{Results with "+k+" clusters}");

                String[] classLabels = VectorTools.getClassLabels();

                System.out.println("instances: " + data.length);
                System.out.println("attributes: " + data[0].length);
                System.out.println("first instance: " + Arrays.toString(data[0])
                        + " class label: " + classLabels[0]);
                System.out.println("last instance: "
                        + Arrays.toString(data[data.length - 1]) + " class label: "
                        + classLabels[data.length - 1]);

                KMeans kmeans = new KMeans(k, data);
                kmeans.assignAndUpdate();

                double sse = kmeans.getSSE();
                System.out.println("SSE " + sse);
                double ssb = kmeans.getSSB();
                System.out.println("SSB " + ssb);
                System.out.println("Sum: " + (sse + ssb));
                System.out.println("Silhouette "
                        + kmeans.getAverageSilhouetteValue());

                System.out.println();
            }


//            for (int k = 2; k <= 8; k++) {
//
//                KMeans kmeans = new KMeans(k, data);
//                kmeans.assignAndUpdate();
//
//                double sse = kmeans.getSSE();
//                System.out.println("" + k + "," + sse);
//            }


        }
	}

}
