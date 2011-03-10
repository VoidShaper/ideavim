package com.maddyhome.idea.vim.ex.range;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003-2005 Rick Maddy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.common.Mark;
import com.maddyhome.idea.vim.group.CommandGroups;

/**
 * Represents the line specified by a mark
 */
public class MarkRange extends AbstractRange {
  /**
   * Create the mark range
   *
   * @param mark   The mark name
   * @param offset The range offset
   * @param move   True if cursor should be moved
   */
  public MarkRange(char mark, int offset, boolean move) {
    super(offset, move);

    this.mark = mark;
  }

  /**
   * Gets the line number specified by this range without regard to any offset.
   *
   * @param editor   The editor to get the line for
   * @param context  The data context
   * @param lastZero True if last line was set to start of file
   * @return The zero based line number, -1 if there is no such mark set in the file
   */
  public int getRangeLine(Editor editor, DataContext context, boolean lastZero) {
    Mark mark = CommandGroups.getInstance().getMark().getFileMark(editor, this.mark);

    if (mark != null) {
      return mark.getLogicalLine();
    }
    else {
      return -1;
    }
  }

  public String toString() {
    StringBuffer res = new StringBuffer();
    res.append("MarkRange[");
    res.append("mark=").append(mark);
    res.append(", ");
    res.append(super.toString());
    res.append("]");

    return res.toString();
  }

  private char mark;
}
