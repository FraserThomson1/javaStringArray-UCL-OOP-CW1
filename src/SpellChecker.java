public class SpellChecker{

    private StringArray unmatched;

    public SpellChecker(){
        unmatched = new StringArray();
    }

    private String InputText(){
        Input in = new Input();
        String input;
        System.out.println("Input your text: ");
        input = in.nextLine();
        return input;
    }

    private String formatString(String s){
        String s_f = s;
        s_f = s_f.replaceAll("\\p{Punct}"," ");
        return s_f;
    }

    private StringArray ParseText(String s){
        StringArray sArr = new StringArray();
        String[] parsed = s.split("\\s+");
        for(String str:parsed){
            sArr.add(formatString(str));
        }
        return sArr;
    }

    private StringArray loadDictionary(){
        StringArray words = new StringArray();
        FileInput input = new FileInput("words.txt");
        while (input.hasNextLine()){
            String s = input.nextLine();
            words.add(s);
        }
        return words;
    }

    private void match_words(StringArray text,StringArray dict){
        for(int i = 0;i < text.size();i ++){
            if(dict.contains(text.get(i))){
                continue;
            }
            else{
                unmatched.add(text.get(i));
            }
        }
    }

    public void run(){
        String text = InputText();
        String formatted_txt = formatString(text);
        StringArray input = ParseText(formatted_txt);
        StringArray dict = loadDictionary();
        match_words(input,dict);

        System.out.println(unmatched.size() + " words were not matched in the dictionary");
        for(int i = 0;i < unmatched.size();i ++) {
            System.out.println(" " + unmatched.get(i) + " ");
        }
    }
}
