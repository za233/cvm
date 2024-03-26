package io.github.r1mao.model;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Graph<M,N> implements Iterable<M>
{
    private final ArrayList<M> nodes=new ArrayList<>();
    private final HashMap<M, ArrayList<Edge<M,N>>> edgeMap=new HashMap<>();
    private final HashMap<M, ArrayList<Edge<M,N>>> edgeMapShadow=new HashMap<>();
    public M addNode(M n)
    {
        this.nodes.add(n);
        this.edgeMap.put(n,new ArrayList<>());
        this.edgeMapShadow.put(n,new ArrayList<>());
        return n;
    }
    public int indexOf(M n)
    {
        return this.nodes.indexOf(n);
    }
    public int nodeCount()
    {
        return this.nodes.size();
    }
    public M node(int index)
    {
        return this.nodes.get(index);
    }
    protected void sanity(M node)
    {
        assert this.nodes.contains(node);
        assert this.edgeMap.containsKey(node);
        assert this.edgeMapShadow.containsKey(node);
    }
    public boolean isEmpty()
    {
        return this.nodes.isEmpty() && this.edgeMapShadow.isEmpty() && this.edgeMap.isEmpty();
    }
    protected void sanity(M from, M to)
    {
        assert this.nodes.contains(from) && this.nodes.contains(to);
        assert this.edgeMap.containsKey(from) && this.edgeMap.containsKey(to);
        assert this.edgeMapShadow.containsKey(from) && this.edgeMapShadow.containsKey(to);
    }
    protected void removeNode(M node)
    {
        sanity(node);
        this.nodes.remove(node);
        this.edgeMap.remove(node);
        this.edgeMapShadow.remove(node);
        for(ArrayList<Edge<M,N>> edges:this.edgeMap.values())
            edges.removeIf(nEdge -> nEdge.getTo().equals(node));
        for(ArrayList<Edge<M,N>> edges:this.edgeMapShadow.values())
            edges.removeIf(nEdge -> nEdge.getFrom().equals(node));

    }
    protected void addEdge(M from,M to,N edgeData)
    {
        sanity(from,to);
        Edge edge=new Edge(from,to,edgeData);
        this.edgeMap.get(from).add(edge);
        this.edgeMapShadow.get(to).add(edge);

    }
    protected void removeEdges(M node)
    {
        HashSet<M> to=new HashSet<>();
        for(Edge<M,N> e:this.getEdges(node))
            to.add(e.getTo());
        for(M n:to)
            this.removeEdge(node,n);
    }
    protected void removeEdge(M from,M to)
    {
        sanity(from,to);
        Predicate<Edge> predicate=nEdge -> nEdge.getFrom().equals(from) && nEdge.getTo().equals(to);
        this.edgeMap.get(from).removeIf(predicate);
        this.edgeMapShadow.get(to).removeIf(predicate);
    }
    protected void removeEdge(M from,M to,N data)
    {
        sanity(from,to);
        Predicate<Edge> predicate=nEdge -> nEdge.getFrom().equals(from) && nEdge.getTo().equals(to) && nEdge.getData().equals(data);
        this.edgeMap.get(from).removeIf(predicate);
        this.edgeMapShadow.get(to).removeIf(predicate);
    }
    protected ArrayList<Edge<M,N>> getEdges(M node)
    {
        sanity(node);
        return this.edgeMap.get(node);
    }
    protected ArrayList<Edge<M,N>> getReverseEdges(M node)
    {
        sanity(node);
        return this.edgeMapShadow.get(node);
    }
    protected ArrayList<M> successors(M node)
    {
        sanity(node);
        ArrayList<M> result=new ArrayList<>();
        for(Edge<M,N> e:this.edgeMap.get(node))
        {
            assert e.getFrom()==node;
            result.add(e.getTo());
        }
        return result;

    }
    protected ArrayList<M> predecessors(M node)
    {
        sanity(node);
        ArrayList<M> result=new ArrayList<>();
        for(Edge<M,N> e:this.edgeMapShadow.get(node))
        {
            assert e.getTo()==node;
            result.add(e.getFrom());
        }
        return result;

    }
    @Override
    public Iterator<M> iterator()
    {
        return this.nodes.iterator();
    }

    @Override
    public void forEach(Consumer<? super M> action)
    {
        this.nodes.forEach(action);
    }

    @Override
    public Spliterator<M> spliterator()
    {
        return this.nodes.spliterator();
    }
}
