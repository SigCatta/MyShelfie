package it.polimi.ingsw.Controller.Server.ServerController;
import java.util.HashMap;

public class TwoValuesHashMap<KeyT, T1, T2> {
    private HashMap<KeyT, Values> map = new HashMap<>();

    public void put(KeyT key, T1 value1, T2 value2) {
        map.put(key, new Values(value1, value2));
    }

    public void put1(KeyT key, T1 value1){
        map.get(key).setValue1(value1);
    }

    public void put2(KeyT key,T2 value2){
        map.get(key).setValue2(value2);
    }

    /**
     * returns the first value of the hashmap
     */
    public synchronized T1 get1(KeyT key){
        if(key == null) return null;
        return map.get(key).getValue1();
    }

    /**
     * returns the second value of the hashmap
     */
    public synchronized T2 get2(KeyT key){
        return map.get(key).getValue2();
    }

    public synchronized void remove(KeyT key){
        map.remove(key);
    }

    public synchronized boolean containsKey(KeyT key){
        return map.containsKey(key);
    }

    public int size(){
        return map.size();
    }

    /**
     * manages the three values
     */
    private class Values {
        private T1 value1;
        private T2 value2;

        public Values(T1 value1, T2 value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        private T1 getValue1() {
            return value1;
        }

        private void setValue1(T1 value1){
            this.value1 = value1;
        }

        private T2 getValue2() {
            return value2;
        }

        private void setValue2(T2 value2){
            this.value2 = value2;
        }
    }
}