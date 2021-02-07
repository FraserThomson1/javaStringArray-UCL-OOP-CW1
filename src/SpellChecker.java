public class SpellChecker{

    private StringArray unmatched; //array storing words that didnt match

    public SpellChecker(){
        unmatched = new StringArray(); //initialise it as an empty string array
    }

    private String InputText(){
        //takes input and returns it
        Input in = new Input();
        String input;
        System.out.println("Input your text: ");
        input = in.nextLine();
        return input;
    }

    private String formatString(String s){
        //removes punctuation and replace them with whitespace
        String s_f = s;
        s_f = s_f.replaceAll("\\p{Punct}"," ");
        return s_f;
    }

    private StringArray ParseText(String s){
        //parse a given string based on position of its white spaces
        StringArray sArr = new StringArray(); //initialise a StringArray to store the words in the string
        String[] parsed = s.split("\\s+"); //finds any length of whitespace
        for(String str:parsed){
            sArr.add(formatString(str)); //store the words in the StringArray
        }
        return sArr;
    }

    private StringArray loadDictionary(){
        //loads the string array into a StringArray
        StringArray words = new StringArray();
        FileInput input = new FileInput("words.txt");
        while (input.hasNextLine()){
            String s = input.nextLine();
            words.add(s);
        }
        return words;
    }

    private void match_words(StringArray text,StringArray dict){
        for(int i = 0;i < text.size();i ++){ //iterate over the words in the input
            if(dict.contains(text.get(i))){
                continue; //continue to next word if the current word is contained in the dictionary
            }
            else{
                unmatched.add(text.get(i)); //if not found in the dictionary then store in the unmatched StringArray
            }
        }
    }

    public void run(){
        String text = InputText();
        String formatted_txt = formatString(text);
        StringArray input = ParseText(formatted_txt);
        StringArray dict = loadDictionary();
        match_words(input,dict);

        WordSuggest WS = new WordSuggest();

        System.out.println(unmatched.size() + " words were not matched in the dictionary");
        for(int i = 0;i < unmatched.size();i ++) {
            System.out.print((i+1) + ": " + unmatched.get(i) + " --- ");
            System.out.print("Suggestions: ");
            WordSuggest.FindSuggestion(unmatched.get(i),dict);
            System.out.println();
        }
    }
}
