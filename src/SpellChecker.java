public class SpellChecker{

    private StringArray unmatched; //array storing words that didn't match
    private int[] indices; //index of unmatched words
    private Input in = new Input();

    public SpellChecker(){
        unmatched = new StringArray(); //initialise it as an empty string array
    }

    private String InputText(String prompt){
        //takes input and returns it
        System.out.print(prompt);
        String input = in.nextLine();
        System.out.println();
        return input;
    }

    private void outputToFile(String text){
        //takes string and writes it to output.txt
        FileOutput out = new FileOutput("output.txt");
        out.writeString(text);
        out.writeEndOfLine();
        out.close();
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

    private void match_words(StringArray text,StringArray dict,WordSuggest W){
        StringArray subs = new StringArray();
        String choice;
        for(int i = 0;i < text.size();i ++){ //iterate over the words in the input
            if(dict.contains(text.get(i))){
                continue; //continue to next word if the current word is contained in the dictionary
            }
            else {
                unmatched.add(text.get(i)); //if not found in the dictionary then store in the unmatched StringArray
                System.out.print(text.get(i) + " was not matched --- Suggestions: "); //output word not matched
                subs = W.FindSuggestion(text.get(i), dict); //outputs substitutes and store it in subs
                System.out.println();
                if(subs.size()>0) { //if substitutes available
                    choice = InputText("Choose a substitute for your word(enter index): ");
                    //wait until a valid index entered
                    while (!((Integer.valueOf(choice) <= subs.size()) && (Integer.valueOf(choice) >= 1))) {
                        System.out.println("Invalid choice, please enter again.");
                        choice = InputText("Choose a substitute for your word(enter index): ");
                    }
                    text.set(i, subs.get(Integer.valueOf(choice) - 1)); //swap in the chosen substitution
                }
            }
        }
    }

    public void run(){
        String text = InputText("Input your text: "); //receive input
        String formatted_txt = formatString(text); //formats string
        StringArray input = ParseText(formatted_txt); //parses string and stores words in a stringArray instance
        StringArray dict = loadDictionary(); //load word.txt into a StringArray instance
        StringArray substitutes = new StringArray();
        WordSuggest WS = new WordSuggest();
        String finalString = "";

        match_words(input,dict,WS); //finds words unmatched and swap valid words in

        System.out.print("Stored Text: ");
        //output the modified string to user
        for(int i = 0; i < input.size();i ++){
           System.out.print(input.get(i)+" ");
           finalString = finalString + input.get(i) + " "; //concatenate the words together
        }

        outputToFile(finalString); //write the final String the output file
    }
}
