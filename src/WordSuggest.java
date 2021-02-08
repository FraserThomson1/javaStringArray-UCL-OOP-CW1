public class WordSuggest {

    public WordSuggest(){}

    private int[][] initialiseArray(int s1, int s2){
        int[][] d = new int[s1+1][s2+1];
        for (int i = 0;i < s1+1;i ++){
            for(int j = 0;j < s2+1;j ++) d[i][j] = 0;
        }

        for(int i = 1;i < s1+1; i ++){
            d[i][0] = i;
        }

        for(int j = 1;j < s2+1; j ++){
            d[0][j] = j;
        }
        return d;
    }

    private boolean DistanceIs1(String str1, String str2){
        int[][] d = initialiseArray(str1.length(),str2.length());
        int substitutionCost;
        for (int j = 1;j < str2.length()+1;j ++){
            for(int i = 1;i < str1.length()+1;i ++){
                if (Character.compare(str1.charAt(i-1),str2.charAt(j-1)) == 0){
                    substitutionCost = 0;
                }
                else{
                    substitutionCost = 1;
                }
                d[i][j] = Math.min(Math.min(d[i-1][j]+1,d[i][j-1]+1),d[i-1][j-1]+substitutionCost);
            }
        }
        return (d[str1.length()][str2.length()] < 2);
    }

    public StringArray FindSuggestion(String word,StringArray dict){
        StringArray subs = new StringArray();
        String d;
        for(int i = 0; i < dict.size();i ++){
            d = dict.get(i);
            if(DistanceIs1(word.toLowerCase(),d.toLowerCase())){
                subs.add(d);
                System.out.print(subs.size() + "." + d + " ");
            }
        }
        return subs;
    }
}
