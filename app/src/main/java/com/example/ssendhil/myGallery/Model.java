package com.example.ssendhil.myGallery;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class Model extends Observable {
    // all url values
    final private Integer[] ids = {
            R.string.burnese_mountain,
            R.string.german_shepherd,
            R.string.golden,
            R.string.samoyed,
            R.string.collie,
            R.string.cutie,
            R.string.lab,
            R.string.bull,
            R.string.husky,
            R.string.poodle
    };
    // default filter value
    private int filter = 0;
    // all icons in the app
    private ArrayList<MyIcon> allMyIcons;
    // filtered icons
    private ArrayList<MyIcon> myIcons;
    private MyRecyclerAdapter rAdapter;
    private static final Model ourInstance = new Model();
    static Model getInstance() {
        return ourInstance;
    }
    // constructor
    Model() {
        myIcons = new ArrayList<>();
    }
    public void setAdapter(MyRecyclerAdapter r){
        rAdapter = r;
    }
    // get method for urls
    public Integer[] getIds() {
        return ids;
    }

    /**
     * Load method to load all the icons
     */
    public void load() {
        myIcons = new ArrayList<>();
        allMyIcons = new ArrayList<>();
        for (int i = 0; i < ids.length; ++i) {
            MyIcon toAdd = new MyIcon(ids[i]);
            myIcons.add(toAdd);
            allMyIcons.add(toAdd);
        }
        filter = 0;
        setChanged();
        notifyObservers();
    }
    /**
     * Clear all the icons to show empty screen
     */
    public void clearAll() {
        myIcons.clear();
        allMyIcons.clear();
        filter = 0;
        setChanged();
        notifyObservers();
    }

    /**
     *     get method for myIcons
     */
    public ArrayList<MyIcon> getMyIcons() {
        return myIcons;
    }

    /**
     * sets rating of icon to new value
     * @param pos position of icon in the ArrayList
     * @param rate value of new ration
     */
    public void setIconRating(int pos, int rate) {
        myIcons.get(pos).setMyRating(rate);
        if ((filter != rate) && (filter != 0)){
            myIcons.remove(myIcons.get(pos));
            setChanged();
            notifyObservers();
        }
    }

    /**
     * change Icon's rating without filtering from myIcons arralist
     * @param pos position of icon in the ArrayList
     * @param rate value of new ration
     */
    public void changeIconRating(int pos, int rate) {
        myIcons.get(pos).setMyRating(rate);
        rAdapter.notifyItemChanged(pos);
    }

    /**
     * sets filter value for rating
     * @param i filter rating value
     */
    public void setFilter(int i) {
        filter = i;
        filterIcons();
        setChanged();
        notifyObservers();
    }

    /**
     * gets current filter value
     * @return current filter value (default - 0)
     */
    public int getFilter() {
        return filter;
    }
    /**
     * filters all the icons based on rating and adds it to myIcons
     */
    private void filterIcons() {
        myIcons.clear();
        if (allMyIcons != null) {
            if (filter == 0){
                myIcons.addAll(allMyIcons);
            }
            else {
                for (MyIcon ic : allMyIcons) {
                    if (ic.getMyRating() == filter) {
                        myIcons.add(ic);
                    }
                }
            }
        }
    }
    // OBSERVER PATTERN METHODS
    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }
    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}