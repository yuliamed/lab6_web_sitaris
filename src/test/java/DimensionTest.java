import Entity.Dimension;
import org.junit.Test;
public class DimensionTest {

    final double expHeight = 4;
    final double expWidth = 5;
    final double expLength = 6;
    final double expWeigh =8;
    final double maxWeigh = 10000;
    Dimension d = new Dimension(expHeight,expLength,expWidth,expWeigh);
    @Test
    public void returnRightString(){
        String expectedStr = "height=" + 4.0 +
                "\nlength=" + 6.0 +
                "\nwidth=" + 5.0 +
                "\nweight=" + 8.0;
        String actualStr = d.toString();
        org.junit.Assert.assertEquals(expectedStr,actualStr);
    }
    @Test
    public void rightGetHeight(){
        double actual = d.getHeight();
        org.junit.Assert.assertEquals(expHeight,actual, 0.00001);
    }

    @Test
    public void rightGetWidth(){
        double actual = d.getWidth();
        org.junit.Assert.assertEquals(expWidth,actual, 0.00001);
    }

    @Test
    public void rightGetWeight(){
        double actual = d.getWeight();
        org.junit.Assert.assertEquals(expWeigh,actual, 0.00001);
    }

    @Test
    public void rightGetLength(){
        double actual = d.getLength();
        org.junit.Assert.assertEquals(expLength,actual, 0.1);
    }

    @Test
    public void rightCreationOfDimension(){
        Dimension d1 = new Dimension(expHeight,expLength,expWidth,expWeigh);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionArgLength(){
        new Dimension(expHeight,expLength,expWidth,maxWeigh);
    }

    @Test
    public void rightConstructorArgLength(){
        new Dimension(expHeight,expLength,expWidth,expWeigh);
    }

}
