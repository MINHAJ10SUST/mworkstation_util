package mworkstation.com.mworkstation.MatchGame;

import android.view.View;

public class SelectObject {
    View f1;
    View f2;
    boolean f1Select;
    boolean f2Select;
    Match item1;
    Match item2;

    public SelectObject() {
    }

    public View getF1() {
        return f1;
    }

    public void setF1(View f1) {
        this.f1 = f1;
    }

    public View getF2() {
        return f2;
    }

    public void setF2(View f2) {
        this.f2 = f2;
    }

    public boolean isF1Select() {
        return f1Select;
    }

    public void setF1Select(boolean f1Select) {
        this.f1Select = f1Select;
    }

    public boolean isF2Select() {
        return f2Select;
    }

    public void setF2Select(boolean f2Select) {
        this.f2Select = f2Select;
    }

    public Match getItem1() {
        return item1;
    }

    public void setItem1(Match item1) {
        this.item1 = item1;
    }

    public Match getItem2() {
        return item2;
    }

    public void setItem2(Match item2) {
        this.item2 = item2;
    }
}
