package ump.PropertiesComparator.comparator.impl;

import ump.PropertiesComparator.comparator.PropertiesComparator;
import ump.PropertiesComparator.model.ComparisonResult;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FuzzyDiff implements PropertiesComparator {
    private static final double SIMILARITY_THRESHOLD=0.8 ;

    @Override
    public ComparisonResult compare(Map<String,String> props1,Map<String,String> props2,String file1,String file2){
        Map<String,String> diff = new HashMap<>();
        diff.putAll(props1.entrySet().stream()
                .filter(e->{
                    String Value2 = props2.get(e.getKey());
                    return Value2==null
                            || !e.getValue().equals(Value2)
                            && getSimilarity(e.getValue(),Value2) < SIMILARITY_THRESHOLD ;
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            String value2 = props2.get(e.getKey());
                            if(value2 == null){
                                return "Introuvable :Valeur dans "+file1+" est : "+e.getValue()+", Introuvable dans "+file2 ;
                            }
                            return "Fuzzy Difference: valeur dans " + file1 + ": " + e.getValue() + ", valeur dans " + file2 + ": " + value2 + " (Similarity: " + String.format("%.2f", getSimilarity(e.getValue(), value2)) + ")";


                        })));
        diff.putAll(props2.entrySet().stream()
                .filter(e-> !props1.containsKey(e.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e-> "Introuvable dans " + file1 + ", Valeur dans " + file2 + ": " + e.getValue()
                )));
        ComparisonResult resultat = new ComparisonResult(file1,file2,diff);
        return resultat;
    }


    private double getSimilarity (String s1,String s2){
        if(s1==null && s2==null){ return 0.0; }
        int distance = levenshteinDistance(s1,s2);
        int max=Math.max(s1.length(),s2.length());
        return 1 -(double) distance/max ;
    }
    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
