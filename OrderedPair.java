import java.util.*;
import java.lang.Math;
/**
 *
 * @author  Athena Knopes
 */
public class OrderedPair
{
   private String x;
   private String y;

   public OrderedPair(String x1, String y1)
   {
      x = x1;
      y = y1;
   }

   public String getX() { return x; }

   public String getY() { return y; }

/*
 *    If you want to compare two OrderedPair objects for equality,
 *    you need to correctly implement this method
 */
   public boolean equals(Object obj)
   {
      if(getX().equals(((OrderedPair)obj).getX()) && getY().equals(((OrderedPair)obj).getY()))
          return true;
      return false;
   }

/*
 *     Not tested
 *     
 *     If you are going to use a HashMap, You MUST (I think) reimplement this method
 */
   public int hashCode()
   {
      return getX().hashCode() + getY().hashCode();
   }

/*
 *     Not tested
 *     
 *     However, I did used this method in getRelationString
 *     AND it was a helpful tool when used in conjunction with the getRelationString
 *     method which GREATLY simplified and shorten the code in some methods
 */
   public String toString()
   {
      return "("+ getX() + "," + getY() + ")";
   }
}