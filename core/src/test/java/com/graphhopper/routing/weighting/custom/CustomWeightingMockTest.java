package com.graphhopper.routing.weighting.custom;

import com.graphhopper.routing.weighting.TurnCostProvider;
import com.graphhopper.routing.weighting.custom.CustomWeighting.Parameters;
import com.graphhopper.util.EdgeIteratorState;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomWeightingMockTest {

    @Test
    public void testCalcEdgeWeightWithMocks() {

        // mocks pour Parameters
        CustomWeighting.EdgeToDoubleMapping edgeToSpeedMapping = mock(CustomWeighting.EdgeToDoubleMapping.class);
        CustomWeighting.EdgeToDoubleMapping edgeToPriorityMapping = mock(CustomWeighting.EdgeToDoubleMapping.class);
        CustomWeighting.MaxCalc maxSpeedCalc = mock(CustomWeighting.MaxCalc.class);
        CustomWeighting.MaxCalc maxPrioCalc = mock(CustomWeighting.MaxCalc.class);

        // valeurs pour doubles
        double distanceInfluence = 0.0;
        double headingPenaltySeconds = 0.0;

        // crée Parameters réel
        Parameters parameters = new Parameters(
                edgeToSpeedMapping,
                maxSpeedCalc,
                edgeToPriorityMapping,
                maxPrioCalc,
                distanceInfluence,
                headingPenaltySeconds
        );

        // mock TurnCostProvider
        TurnCostProvider turnCostProvider = mock(TurnCostProvider.class);

        // classe testée
        CustomWeighting weighting = new CustomWeighting(turnCostProvider, parameters);

        // mock EdgeIteratorState
        EdgeIteratorState edge = mock(EdgeIteratorState.class);
        when(edge.getDistance()).thenReturn(100.0);

        // mock edgeToPriorityMapping.get(...) pour éviter NullPointerException
        when(edgeToPriorityMapping.get(edge, false)).thenReturn(1.0);

        double weight = weighting.calcEdgeWeight(edge, false);

        assertTrue(weight >= 0, "Le poids doit être positif");
    }
}
