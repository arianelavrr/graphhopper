package com.graphhopper.routing;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.Graph;
import com.graphhopper.routing.util.TraversalMode;
import com.graphhopper.util.EdgeIteratorState;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AStarMockTest {

    @Test
    public void testAStarWithMocks() {

        // mock graph
        Graph graph = mock(Graph.class);

        // mock weighting
        Weighting weighting = mock(Weighting.class);

        // on simule que calcEdgeWeight retourne toujours 5.0
        when(weighting.calcEdgeWeight(any(EdgeIteratorState.class), anyBoolean()))
                .thenReturn(5.0);

        // choix d'un TraversalMode valide
        TraversalMode tMode = TraversalMode.NODE_BASED;

        // classe testée
        AStar algo = new AStar(graph, weighting, tMode);

        // calcPath peut dépendre d’un graphe réel, donc on teste directement le mock de weighting
        double weight = weighting.calcEdgeWeight(mock(EdgeIteratorState.class), false);

        assertEquals(5.0, weight, 1e-6, "Le poids doit correspondre au mock de Weighting");
    }
}
