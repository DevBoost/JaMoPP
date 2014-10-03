/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.List;

/**
 * A class which can be overridden to customize code completion proposals.
 */
public class JavaProposalPostProcessor {
	
	public List<org.emftext.language.java.resource.java.ui.JavaCompletionProposal> process(List<org.emftext.language.java.resource.java.ui.JavaCompletionProposal> proposals) {
		// the default implementation does returns the proposals as they are
		return proposals;
	}
	
}
