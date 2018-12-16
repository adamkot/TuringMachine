package pl.kotus.turingmachine.utils;

/**
 *
 * @author Adam
 */
public class Machine {

    String[][] text;
    String state = "Start";
    String nstate;
    char saint;
    char nsaint;
    char direction;

    public void setText(String[][] text) {
        this.text = text;
    }

    public String[][] getText() {
        return this.text;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setNstate(String nstate) {
        this.nstate = nstate;
    }

    public String getNstate() {
        return this.nstate;
    }

    public void setSaint(char saint) {
        this.saint = saint;
    }

    public char getSaint() {
        return this.saint;
    }

    public void setNsaint(char nsaint) {
        this.nsaint = nsaint;
    }

    public char getnSaint() {
        return this.nsaint;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }

    public boolean setOperation(String state, char saint) {
        boolean ret = false;
        for (int i = 0; i < this.text.length; i++) {
            if (text[i][0].equals(state) && text[i][1].charAt(0) == saint) {
                this.setState(text[i][0]);
                this.setSaint(text[i][1].charAt(0));
                this.setNsaint(text[i][1].charAt(2));
                this.setDirection(text[i][2].charAt(0));
                this.setNstate(text[i][3]);
                ret = true;
            }
        }
        return ret;
    }

    public void reader(String text) {
        String[] tmp = new String[100];
        tmp = text.split("\n");
        String[][] newText = new String[tmp.length][4];
        for (int i = 0; i < tmp.length; i++) {
            newText[i] = line(tmp[i]);
        }
        this.text = newText;
    }

    private String[] line(String text) {
        String[] tmp;
        String[] newText = new String[4];
        tmp = text.split(":");
        System.arraycopy(tmp, 0, newText, 0, 4);
        return newText;
    }

    public String compilation() {
        String error = "";
        if (text[0][0].equals("Start") || text[0][0].equals("start")) {
            for (int i = 0; i < this.text.length; i++) {
                if (text[i][1].length() != 3) {
                    error = "Błąd w linii " + (i + 1) + " zła liczba znaków ";
                } else if (text[i][1].charAt(1) != '/') {
                    error = error + "\n" + "Błąd w linii " + (i + 1) + " błąd formuły zmiany znaku " + "znak " + text[i][1].charAt(1);
                }
                if (text[i][2].length() > 1) {
                    error = error + "\n" + "Błąd w linii " + (i + 1) + " błąd w zapisie kierunku ";
                }
                if (text[i][2].charAt(0) != 'P' && text[i][2].charAt(0) != 'p'
                        && text[i][2].charAt(0) != 'l' && text[i][2].charAt(0) != 'L') {
                    error = error + "\n" + "Błąd w linii " + (i + 1) + " błąd w zapisie kierunku, znaleziono " + text[i][2].charAt(0) + " (P/L)";
                }
            }
        } else {
            error = "Brak stanu Start!";
        }
        return error;
    }
}
