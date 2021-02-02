public class StringArray {

    private String arr[];
    private int size;

    public StringArray(){
        arr = new String[100];
        size = 0;
    }

    public StringArray(StringArray a){
        size = a.size() + (100 - a.size() % 100);
        arr = new String[size];

        for(int i = 0;i < a.size();i ++){
            arr[i] = a.get(i);
        }

        size = a.size();
    }

    private void increase_size(){
        if(arr.length == this.size()){
            String[] tmp = arr;
            arr = new String[tmp.length+100];
            for(int i = 0;i < tmp.length;i ++){
                arr[i] = tmp[i];
            }
        }
    }

    private boolean indexValid(int index){
        if((index > (this.size() - 1)) || (index < 0)){
            return false;
        }
        return true;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if (this.size() == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public String get(int index){
        if(!indexValid(index)){
            return null;
        }
        else {
            return arr[index];
        }
    }

    public void set(int index, String s){
        if (indexValid(index)){
            arr[index] = s;
        }
    }

    public void add(String s){
        increase_size();
        int index = this.size();
        arr[index] = s;

        size ++;
    }

    public void insert(int index, String s){
        increase_size();

        if (this.isEmpty()){
            if (index == 0){
                arr[0] = s;
                size++;
            }
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
            this.set(index,null);
            for(int i = index+1;i < this.size(); i ++){
                arr[i-1] = arr[i];
            }
            size--;
        }
    }

    public boolean contains(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return true;
                }
            }
            else if(arr[i] == null){
                continue;
            }
            else if((arr[i].toLowerCase()).equals(s.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public boolean containsMatchingCase(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return true;
                }
            }
            else if(arr[i] == null){
                continue;
            }
            else if((arr[i].equals(s))){
                return true;
            }
        }
        return false;
    }

    public int indexOf(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return i;
                }
            }
            else if(arr[i] == null){
                continue;
            }
            if(arr[i] == null){
                if (s == null){
                    return i;
                }
                else{
                    continue;
                }
            }
            else if((arr[i].toLowerCase()).equals(s.toLowerCase())){
                return i;
            }
        }

        return -1;
    }

    public int indexOfMatchingCase(String s){
        for (int i = 0;i < this.size();i ++){
            if(s == null){
                if (arr[i] == null){
                    return i;
                }
            }
            else if(arr[i] == null){
                continue;
            }
            else if(arr[i].equals(s)){
                return i;
            }
        }
        return -1;
    }
}


