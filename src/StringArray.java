public class StringArray {
    private String arr[]; //stores strings
    private int size; //stores num of items of the string array

    public StringArray(){
        arr = new String[100]; //initialise the string array to size  100
        size = 0; //initialise size of array to 0
    }

    public StringArray(StringArray a){
        int len = a.size() + (100 - a.size() % 100); //calculate correct size
        arr = new String[len]; //initialise string array to correct size

        //copy the parameter array into the string array
        for(int i = 0;i < a.size();i ++){
            arr[i] = a.get(i);
        }

        size = a.size(); //update the size
    }

    private void increase_size(){
        if(arr.length == this.size()){ //check if array is full
            String[] tmp = arr;
            arr = new String[tmp.length+100]; //extends length of string array by 100 elements
            for(int i = 0;i < tmp.length;i ++){
                arr[i] = tmp[i]; //copy original strings to new string array
            }
        }
    }

    private boolean indexValid(int index){
        return (index <= (this.size() - 1)) && (index >= 0); //checks if an index if valid for the current string array
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

    public String get(int index){
        if(!indexValid(index)){
            return null; //if index not valid then return null
        }
        else {
            return arr[index]; //return item in the string array at that index
        }
    }

    public void set(int index, String s){
        if (indexValid(index)){
            arr[index] = s; //if index valid overwrite item at index
        }
    }

    public void add(String s){
        increase_size(); //increase the size of the string array if it is full
        int index = this.size(); //index set to next index of current last item
        arr[index] = s;

        size ++; //increment size
    }

    public void insert(int index, String s){
        increase_size(); //increase the size of the string array if it is full

        if (this.isEmpty()){
            if (index == 0){ arr[0] = s; size++; } //add item if array is empty
        }
        else if (indexValid(index)){
            String prev = this.get(index);
            String tmp;
            for (int i = index+1; i < this.size(); i ++){
                tmp = arr[i];
                arr[i] = prev;
                prev = tmp;
            }
            this.set(index,s);
            size++;
        }

    }

    public void remove(int index){
        if(indexValid(index)){
            this.set(index,null); //set removed item's index to null
            for(int i = index+1;i < this.size(); i ++){
                arr[i-1] = arr[i]; //move items behind the removed item 1 index to the front
            }
            size--; //decrement size
        }
    }

    public boolean contains(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return true; //if search item is null and item at index is null return true
                }
            }
            else if(arr[i] != null) {
                if ((arr[i]).equalsIgnoreCase(s)) { //compares the strings ignoring the case.
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsMatchingCase(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return true; //if search item is null and item at index is null return true
                }
            }
            else if(arr[i] != null) {
                if ((arr[i].equals(s))) { //compares the strings considering their case
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return i; //if search item is null and item at index i is null return i
                }
            }
            else if(arr[i] != null) {
                if ((arr[i]).equalsIgnoreCase(s)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indexOfMatchingCase(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return i; //if search item is null and item at index i is null return i
                }
            }
            else if(arr[i] != null) {
                if (arr[i].equals(s)) {
                    return i;
                }
            }
        }
        return -1;
    }
}


