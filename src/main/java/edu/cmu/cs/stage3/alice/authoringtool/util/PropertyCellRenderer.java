/*
 * Copyright (c) 1999-2003, Carnegie Mellon University. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * 3. Products derived from the software may not be called "Alice",
 *    nor may "Alice" appear in their name, without prior written
 *    permission of Carnegie Mellon University.
 * 
 * 4. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    "This product includes software developed by Carnegie Mellon University"
 */

package edu.cmu.cs.stage3.alice.authoringtool.util;

import javax.swing.JTable;

public class PropertyCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
	protected ColorRenderer colorRenderer = new ColorRenderer();

	public PropertyCellRenderer() {}

	
	public java.awt.Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
		super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );

		String toolTipText = null;
		if( value != null ) {
			if( value instanceof edu.cmu.cs.stage3.alice.core.Element ) {
				toolTipText = ((edu.cmu.cs.stage3.alice.core.Element)value).getKey();
			} else if( value instanceof java.lang.String ) {
				toolTipText = "\"" + getText() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				toolTipText = getText();
			}
			String classString = value.getClass().getName();
			if( classString.startsWith( "edu.cmu.cs.stage3." ) ) { //$NON-NLS-1$
				classString = classString.substring( "edu.cmu.cs.stage3.".length() ); //$NON-NLS-1$
			}
			toolTipText = toolTipText + " (" + classString + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		this.setToolTipText( toolTipText );

		if( value instanceof edu.cmu.cs.stage3.alice.core.Element ) {
			setText( edu.cmu.cs.stage3.alice.authoringtool.AuthoringToolResources.getReprForValue( value ) );
		} else if( (value instanceof java.awt.Color) || (value instanceof edu.cmu.cs.stage3.alice.scenegraph.Color) ) {
			colorRenderer.setToolTipText( toolTipText );
			return colorRenderer.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
		} else if( value != null && value.getClass().isArray() ) {
			String text = ""; //$NON-NLS-1$
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			nf.setMaximumFractionDigits( 3 );
			text = "{ "; //$NON-NLS-1$
			int m = java.lang.reflect.Array.getLength( value );
			for( int i = 0; i < m; i++ ) {
				Object o = java.lang.reflect.Array.get( value, i );
				if( o.getClass().isArray() ) {
					text += "{ "; //$NON-NLS-1$
					int n = java.lang.reflect.Array.getLength( o );
					for( int j=0; j<n; j++ ) {
						Object p = java.lang.reflect.Array.get( o, j );
						if( p instanceof Number ) {
							text += nf.format( p );
						} else {
							text += p;
						}
						if( j<n-1 ) {
							text += ", "; //$NON-NLS-1$
						}
						else {
							text += " "; //$NON-NLS-1$
						}
					}
					text += "}"; //$NON-NLS-1$
				} else {
					if( o instanceof edu.cmu.cs.stage3.alice.core.Element ) {
						text += edu.cmu.cs.stage3.alice.authoringtool.AuthoringToolResources.getReprForValue( o );
					} else if( o instanceof Number ) {
						text += nf.format( o );
					} else {
						text += o;
					}
				}
				if( i < m - 1 ) {
					text += ", "; //$NON-NLS-1$
				} else {
					text += " "; //$NON-NLS-1$
				}
				if( text.length() > 64 ) {
					text += "..."; //$NON-NLS-1$
					break;
				}
			}
			text += "}"; //$NON-NLS-1$
			setText( text );
		} else if( value instanceof edu.cmu.cs.stage3.util.Enumerable ) {
			setText( ((edu.cmu.cs.stage3.util.Enumerable)value).getRepr() );
		} else if( value instanceof java.util.Enumeration ) {
			setText( Messages.getString("PropertyCellRenderer.16") ); //$NON-NLS-1$
		}

		return this;
	}

	class ColorRenderer extends javax.swing.table.DefaultTableCellRenderer {
		java.util.Hashtable colorsToIcons = new java.util.Hashtable();

		public ColorRenderer() {}

		
		public java.awt.Component getTableCellRendererComponent( JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column ) {
			if( color instanceof edu.cmu.cs.stage3.alice.scenegraph.Color ) {
				edu.cmu.cs.stage3.alice.scenegraph.Color c = (edu.cmu.cs.stage3.alice.scenegraph.Color)color;
				color = new java.awt.Color( c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha() );
			}

			setBackground( isSelected ? table.getSelectionBackground() : table.getBackground() );

			javax.swing.Icon icon = (javax.swing.Icon)colorsToIcons.get( color );
			if( icon == null ) {
				int height = table.getRowHeight() - 4;
				int width = height * 2;
				java.awt.image.BufferedImage colorImage = new java.awt.image.BufferedImage( width, height, java.awt.image.BufferedImage.TYPE_INT_RGB );
				java.awt.Graphics2D g = colorImage.createGraphics();
				g.setColor( (java.awt.Color)color );
				g.fill3DRect( 0, 0, width, height, true );
				icon = new javax.swing.ImageIcon( colorImage );
				colorsToIcons.put( color, icon );
			}

			setIcon( icon );
			setFont( table.getFont() );
			setText( getTextFromColor( (java.awt.Color)color ) );

			return this;
		}

		public String getTextFromColor( java.awt.Color color ) {
			String text = ""; //$NON-NLS-1$
			if( color.equals( java.awt.Color.black ) ) { text = Messages.getString("PropertyCellRenderer.18"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.blue ) ) { text = Messages.getString("PropertyCellRenderer.19"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.cyan ) ) { text = Messages.getString("PropertyCellRenderer.20"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.darkGray ) ) { text = Messages.getString("PropertyCellRenderer.21"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.gray ) ) { text = Messages.getString("PropertyCellRenderer.22"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.green ) ) { text = Messages.getString("PropertyCellRenderer.23"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.lightGray ) ) { text = Messages.getString("PropertyCellRenderer.24"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.magenta ) ) { text = Messages.getString("PropertyCellRenderer.25"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.orange ) ) { text = Messages.getString("PropertyCellRenderer.26"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.pink ) ) { text = Messages.getString("PropertyCellRenderer.27"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.red ) ) { text = Messages.getString("PropertyCellRenderer.28"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.white ) ) { text = Messages.getString("PropertyCellRenderer.29"); } //$NON-NLS-1$
			else if( color.equals( java.awt.Color.yellow ) ) { text = Messages.getString("PropertyCellRenderer.30"); } //$NON-NLS-1$
			else {
				float[] rgba = new float[4];
				color.getComponents( rgba );
				text = Messages.getString("PropertyCellRenderer.31") + rgba[0] + Messages.getString("PropertyCellRenderer.32") + rgba[1] + Messages.getString("PropertyCellRenderer.33") + rgba[2] + Messages.getString("PropertyCellRenderer.34") + rgba[3] + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			}

			return text;
		}
	}
}
