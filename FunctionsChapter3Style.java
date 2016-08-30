/*    implewment (almost) ALL the methods in this class.
 * 
 *    But first, you need to finish implementing the OrderedPair class
 *    
 *    The first tester provided test the OrderedPair class methods equals and hashCode
 *    which I believe are important methods that you may use or I use in the tester when I create
 *    a HashSet.  This tester method is only for your benefit as it is not included the stipulator tester.
 */

import java.util.*;
import java.lang.Math;
/**
 *
 * @author  Athena Knopes
 * 
 */
public class FunctionsChapter3Style
{
    Set<String> domain=new HashSet<String>();
    Set<String> coDomain=new HashSet<String>();
    Set<OrderedPair> relation=new HashSet<OrderedPair>();
    public FunctionsChapter3Style(Set<String> d, Set<String> co)
    {
        domain=d;
        coDomain=co;
    }

    public FunctionsChapter3Style(Set<String> d, Set<String> co, Set<OrderedPair> r)
    {
        domain=d;
        coDomain=co;
        relation=r;
    }

    /*
     *    Not test.
     *    
     *    I admit that I did use this method a couple of times.
     *    It came in very handy and SHORTEN my code and GREATLY simplified some methods
     *    I will let you figure out which ones and how
     *    
     *    returns a String of the form: [(a,b), (c,d), ...(v,w)]
     */
    public String getRelString()
    {
        String ans="[";
        Iterator<OrderedPair> it=relation.iterator();
        while(it.hasNext())
        {
            OrderedPair temp=it.next();
            ans+="(" + temp.getX() + "," + temp.getY() + "), ";
        }
        ans=ans.substring(0,ans.length()-2);
        return ans+"]";
    }

    /*
     *    replaces the current relation instance variable with op
     *    
     *    YES - this method gets used in the my (stipulator) tester
     */
    public void setRelation(Set<OrderedPair> op)
    {
        relation.clear();
        Iterator<OrderedPair> it=op.iterator();
        while(it.hasNext())
            relation.add(it.next());
    }

    /*
     *    returns the current relation instance variable 
     */
    public Set<OrderedPair> getRelation()
    {  
        return relation;
    }

    /*
     *    retruns the number of Order Pairs in the relation
     */
    public int size()
    {
        return relation.size();
    }

    /*
     *    returns true if the array of Order Pairs forms a function
     *    returns false otherwise
     *    every x has a y
     */
    public boolean isFunction()
    {
        Iterator<OrderedPair> ito=relation.iterator();
        String ss=getRelString();
        Set<String> xs= new TreeSet<String>();
        ArrayList<String> temp=new ArrayList<String>();
        while(ito.hasNext())
        {
            String x=ito.next().getX();
            xs.add(x);
            for(int i=0; i<temp.size(); i++)
            {
                if(temp.get(i).equals(x))
                    return false;
            }
            temp.add(x);
        }
        return domain.size()==xs.size();
    }

    /* 
     *    A function f from X to Y is said to be one to one if 
     *    for each y in Y, there is at most one x in X with f(x) = y
     *
     *    returns true if array of order Pairs is a function and the function is one to one
     *    returns false otherwise
     */
    public boolean isOneToOne()
    {
        Iterator<OrderedPair> ito=relation.iterator();
        if(!isFunction())
            return false;
        Set<String> ys= new TreeSet<String>();
        ArrayList<String> temp=new ArrayList<String>();
        while(ito.hasNext())
        {
            String y=ito.next().getY();
            ys.add(y);
            for(int i=0; i<temp.size(); i++)
            {
                if(temp.get(i).equals(y))
                    return false;
            }
            temp.add(y);
        }
        return domain.size()==ys.size();
    }

    /*
     *    A function from X to Y is said to be onto if
     *    the range of f == Y
     *
     *    returns true if array of order Pairs is a function and the function is onto
     *    returns false otherwise
     */
    public boolean isOnTo()
    {
        return isFunction() && isOneToOne();
    }

    /*
     *     returns true if the array of order Pairs is a function and the function is bijective
     *              that is both one to one and onto
     *     returns false otherwise
     */
    public boolean isBijective()
    {
        return isOneToOne() && isOnTo();
    }

    /*
     *   precondition:  rel and op are functions.  Domain of op subset of coDomain of rel
     *   
     *   returns a new FunctionsChapter3Style Object.
     *   The domain is the domain of new Object is this.domain
     *   The coDomain is opCoDomain (the paramenter)
     *   
     *   The new function is the composition //op( getRelation (domain) )
     */
    public FunctionsChapter3Style composition(Set<OrderedPair> op, Set<String> opCoDomain)
    {
        FunctionsChapter3Style ans = new FunctionsChapter3Style(opCoDomain, opCoDomain, new HashSet<OrderedPair>() );
        Set<OrderedPair> rel=getRelation();
        Iterator<OrderedPair> it=rel.iterator();
        Set<OrderedPair> tempList=new HashSet<OrderedPair>();
        while(it.hasNext())
        {
            Iterator<OrderedPair> it2=op.iterator();
            OrderedPair tempRel=it.next();
            while(it2.hasNext())
            {
                OrderedPair tempOp=it2.next();
                if(tempRel.getY().equals(tempOp.getX()))
                    tempList.add(new OrderedPair(tempRel.getX(),tempOp.getY()));
            }
        }
        ans=new FunctionsChapter3Style(domain, opCoDomain, tempList);
        return ans;
    }

    /*
     *   precondition:  rel is a function.
     *   rel does not have to be both 1-1 and onto
     *   the inverse does not need to be a function
     */
    public OrderedPair[] getInverse()
    {
        ArrayList<OrderedPair> arr=new ArrayList<OrderedPair>();
        Iterator<OrderedPair> it=relation.iterator();
        while(it.hasNext())
        {
            OrderedPair temp=it.next();
            arr.add(new OrderedPair(temp.getY(), temp.getX()));
        }
        OrderedPair[] ans = new OrderedPair[arr.size()];
        for(int x=0; x<ans.length; x++)
            ans[x]=arr.get(x);
        return ans;
    }

    /*
     * A relation is reflexive if (x, x) in R for every x in X
     * 
     *       returns true if the current relation is reflexive
     *       returns false otherwise
     */
    public boolean isReflexive()
    {
        Iterator<String> itx=domain.iterator();
        while(itx.hasNext())
        {
            boolean test=false;
            String tempx=itx.next();
            Iterator<OrderedPair> itop=relation.iterator();
            while(itop.hasNext())
            {
                OrderedPair tempop=itop.next();
                if(tempx.equals(tempop.getY()) && tempx.equals(tempop.getX()))
                    test=true;
            }
            if(test==false)
                return false;
        }
        return true;
    }

    /*
     *       A relation is symmetric if
     *       for all x, y in X, if (x,y) in R, then (y,x) in R
     * 
     *       returns true if the current relation is symmetric
     *       returns false otherwise
     */
    public boolean isSymmetric()
    {
        Iterator<OrderedPair> it=relation.iterator();
        while(it.hasNext())
        {
            OrderedPair temp=it.next();
            Iterator<OrderedPair> it2=relation.iterator();
            boolean test=false;
            while(it2.hasNext())
            {
                OrderedPair temp2=it2.next();
                if(temp.getX().equals(temp2.getY()) && temp.getY().equals(temp2.getX()))
                    test=true;
            }
            if(test==false)
                return false;
        }
        return true;
    }

    /*
     *       A relation is Antisymmetric if
     *       for all x, y in X, if (x,y) in R, and (y,x) in R, then x = y
     * 
     *    returns true if the current relation is Antisymmetric
     *    returns false otherwise
     *    
     *     if R(a,b) and R(b,a), then a = b, or, equivalently, if R(a,b) with a ? b, then R(b,a) must not hold.
     */
    public boolean isAntiSymmetric()
    {
        Iterator<OrderedPair> it1=relation.iterator();
        while(it1.hasNext())
        {
            OrderedPair temp=it1.next();
            if(relation.contains(new OrderedPair(temp.getY(), temp.getX())) && !temp.getY().equals(temp.getX()))
                return false;
        }
        return true;
    }

    /*
     *       A relation is transitive:
     *       if (a,b) and (b,c) then (a,c)
     * 
     *       returns true if the current relation is reflexive
     *       returns false otherwise
     */
    public boolean isTransitive()
    {
        if(isReflexive())
            return true;
        Iterator<OrderedPair> it=relation.iterator();
        
        while(it.hasNext())
        {
            OrderedPair temp1=it.next();
            Iterator<OrderedPair> it2=relation.iterator();
            while(it2.hasNext())
            {
                OrderedPair temp2=it2.next();
                if(temp1.getY().equals(temp2.getX()))
                {
                    if(!relation.contains(new OrderedPair(temp1.getX(), temp2.getY())))
                        return false;
                }
            }
        }
        return true;
    }

    /*
     *    returns true is the relation is an Equivalence Relation
     *    returns false otherwise
     */
    public boolean isEquivalenceRelation()
    {
        return isSymmetric() && isReflexive() && isTransitive();
    }

    /*
     * 
     *    returns true is the relation is an Partially Order
     *    returns false otherwise
     */
    public boolean isPartiallyOrder()
    {
        return isAntiSymmetric() && isReflexive() && isTransitive();
    }

    /*
     *      not tested.
     *      Not sure why it is here.  I think I needed/used this functionality more than once,
     *      and therefore created a helper method
     */
    public ArrayList<OrderedPair> getRel(String s)
    {
        ArrayList<OrderedPair> ans = new ArrayList<OrderedPair>();

        return ans;
    }

    public boolean hasSymmetricPairs()
    {
        Iterator<OrderedPair> it1=relation.iterator();
        while(it1.hasNext())
        {
            OrderedPair temp1=it1.next();
            Iterator<OrderedPair> it2=relation.iterator();
            while(it2.hasNext())
            {
                OrderedPair temp2=it2.next();
                if(temp1.getX().equals(temp2.getY()) && temp1.getY().equals(temp2.getX()))
                    return true;
            }
        }
        return false;
    }
}