/**
** Classic recursion usecase
*/


class GenerateBracketSequences {

    /*
     * Complete the 'generateAngleBracketSequences' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER n as parameter.
     */
     
     static List<String> ans;

    public static List<String> generateAngleBracketSequences(int n) {
        
        ans = new ArrayList<>();
        
        recur(0, 0, "", n*2);
        
        return ans;

    }
    
    static void recur(int open, int len, String s, int target) {
        
        if(len == target) {
            if(open==0) {
                ans.add(s);
            }
            return;
        }
        
        if(open>0) {
            recur(open+1, len+1, s+"<", target);
            recur(open-1, len+1, s+">", target);
        } else {
            recur(open+1, len+1, s+"<", target);
        }
    }

}
