package second.junit;

public class AdvanceMath {

    public int addition(int a, int b) {
        return a+b;
    }

    public int addition(String a, int b) {
        Integer i = Integer.valueOf(a);
        return i+b;
    }

    public int multiply(int a, int b) throws Exception {
        long la = a;
        long lb = b;
        if (la*lb > Integer.MAX_VALUE) throw new Exception("value over the limit");
        return a*b;
    }

    public boolean projekt_1(int hours, int minutes)
    {
        boolean przerwa = false;
        switch(hours)
        {
            case 8:
                if(minutes >= 15) {
                    przerwa = false;
                }
                break;
            case 9:
                if(minutes < 45) {
                    przerwa = false;
                }
                else
                {
                    przerwa = true;
                }
                break;
            case 10:
                przerwa = false;
                break;
            case 11:
                if(minutes < 30 || minutes >= 45)
                {
                    przerwa = false;
                }
                else
                {
                    przerwa = true;
                }
                break;
            case 12:
                przerwa = false;
                break;
            case 13:
                if(minutes < 15 || minutes >= 45) {
                    przerwa = false;
                }
                else
                {
                    przerwa = true;
                }
                break;
            case 14:
                przerwa = false;
                break;
            case 15:
                if(minutes < 15 || minutes >= 30) {
                    przerwa = false;
                }
                else
                {
                    przerwa = true;
                }
                break;
            case 16:
                przerwa = false;
                break;
            case 17:
                if(minutes < 15) {
                    przerwa = true;
                }
                else
                {
                    przerwa = false;
                }
                break;
            case 18:
                if(minutes <= 45)
                {

                }
                break;
            default:
                break;
        }
        return przerwa;
    }



}
