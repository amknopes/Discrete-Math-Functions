# Discrete-Math-Functions
This is a java program that demonstrates many basic functions in discrete mathematics and set theory.  
The class FunctionsChapter3Style uses OrderedPair objects within its methods.  
FunctionsChapter3Style has global sets domain, codomain, and relation.  

FunctionsChapter3Style  
>getRelString()  
>>Returns relation in the form "[(a,b), (c,d), ...(v,w)]"

>setRelation(Set<OrderedPair> op)  
>>Replaces relation with op  

>getRelation()  
>>Returns relation

>size()
>>Returns the number of ordered pairs in relation

>isFunction()
>>Returns true if relation's ordered pairs form a function and false otherwise

>isOneToOne()
>>Returns true if relation's ordered pairs form a function and that function is one to one. Returns false otherwise

>isOnTo()
>>Returns true if relation's ordered pairs form a function and that function is onto. Returns false otherwise

>isBijective()
>>Returns true if relation's ordered pairs form a function and that function is bijective. Returns false otherwise

>composition(Set<OrderedPair> op, Set<String> opCoDomain)
>>Returns a new FunctionsChapter3Style object. Its domain is this.domain. Its codomain is opCoDomain. Its relation is the composition of its domain and codomain.

>getInverse()
>>Returns relation's inverse

>isReflexive()
>>Returns true if relation is reflexive and false otherwise

>isSymmetric()
>>Returns true if relation is symmetric and false otherwise

>isAntiSymmetric()
>>Returns true if relation is antisymmetric and false otherwise

>isTransitive()
>>Returns true if relation is reflexive and false otherwise

>isEquivalenceRelation()
>>Returns true if relation is an equivalence relation and false otherwise

>isPartiallyOrder()
>>Returns true if relation is partially ordered and false otherwise

>hasSymmetricPairs()
>>Returns true if relation has symmetric pairs and false otherwise
