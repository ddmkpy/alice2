/*
 * Created on Jun 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package edu.cmu.cs.stage3.alice.core.responses;

/**
 * @author caitlin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FadeToBlackAnimation extends AbstractFadeAnimation {
	public class RuntimeFadeToBlackAnimation extends RuntimeAbstractFadeAnimation {
		
		
		protected boolean endsBlack() {
			return true;
		}
	}
}
