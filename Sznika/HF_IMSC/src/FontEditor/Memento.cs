using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FontEditor.Documents
{
    public partial class CharDef
    {
        public class Memento
        {
            private CharDef mementoCharDef;

            public char CharDefChar {  get { return mementoCharDef.Character; } }

            public Memento(CharDef d)
            {
                mementoCharDef = d.Clone();   
            }

            public void GetState(out bool[,] pixels, out char c)
            {
              
                pixels = mementoCharDef.pixels;
                c = mementoCharDef.character;

            }
        }
    }
}
