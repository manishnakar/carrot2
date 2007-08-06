
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2007, Dawid Weiss, Stanisław Osiński.
 * Portions (C) Contributors listed in "carrot2.CONTRIBUTORS" file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.demo.settings;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;

import org.carrot2.filter.stc.StcConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * A settings panel for {@link org.carrot2.demo.settings.StcSettings}. 
 *  
 * @author Dawid Weiss
 */
class StcSettingsDialog extends JPanel {
    private final transient StcSettings settings;

    public StcSettingsDialog(StcSettings settings) {
        this.settings = settings;
        buildGui();
    }

    private void buildGui() {
        this.setLayout(new BorderLayout());
        
        final DefaultFormBuilder builder = 
            new DefaultFormBuilder(new FormLayout("pref", ""));
        
        builder.appendSeparator("Input preprocessing");

        builder.append(ThresholdHelper.createIntegerThreshold(settings, StcConstants.IGNORED_WORD_IF_IN_FEWER_DOCS,
                "Ignore word if in fewer docs:", 2, 10, 1, 1));
        builder.nextLine();
        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.IGNORED_WORD_IF_IN_MORE_DOCS,
                "Ignore word if in more docs (%):", 0, 1, 0.1, 0.25));

        builder.appendSeparator("Base clusters");

        builder.append(ThresholdHelper.createIntegerThreshold(settings, StcConstants.MAX_BASE_CLUSTERS_COUNT,
                "Max base clusters:", 25, 500, 0, (500-25)/4));
        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.MIN_BASE_CLUSTER_SCORE,
                "Min base cluster score:", 0, 10, 1, 1));
        builder.append(ThresholdHelper.createIntegerThreshold(settings, StcConstants.MIN_BASE_CLUSTER_SIZE,
                "Min base cluster size:", 2, 20, 0, 4));

        builder.appendSeparator("Merging and output");

        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.MERGE_THRESHOLD,
                "Merge threshold:", 0, 1, 0.1, 0.25));
        builder.append(Box.createRigidArea(new Dimension(0, 5)));
        builder.append(ThresholdHelper.createIntegerThreshold(settings, StcConstants.MAX_CLUSTERS,
                "Max clusters:", 2, 30, 0, 4));

        builder.appendSeparator("Label creation");
        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.MAX_PHRASE_OVERLAP,
                "Max phrase overlap:", 0, 1, 0.1, 0.25));
        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.MOST_GENERAL_PHRASE_COVERAGE,
                "Most general p. coverage:", 0, 1, 0.1, 0.25));

        builder.appendSeparator("Base Cluster Boosts");

        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.SINGLE_TERM_BOOST,
                "Single Term Boost:", 0, 3, 0.1, 0.5));
        builder.append(ThresholdHelper.createIntegerThreshold(settings, StcConstants.OPTIMAL_PHRASE_LENGTH,
                "Optimal Phrase Length:", 1, 10, 1, 2));
        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.OPTIMAL_PHRASE_LENGTH_DEV,
                "Phrase Length Tolerance:", 0.5, 5.5, 0.25, 1));
        builder.append(ThresholdHelper.createDoubleThreshold(settings, StcConstants.DOCUMENT_COUNT_BOOST,
                "Document Count Boost:", 0, 5, 0.25, 1));

        this.add(builder.getPanel(), BorderLayout.CENTER);
    }
}
