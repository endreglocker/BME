using FontEditor.Views;

namespace FontEditor.Documents
{
    /// <summary>
    /// Egy adott betűtípus adott karakterének pixeljeit tartalmazza.
    /// Független mindennemű megjelenítéstől, a megjelenítéshez kapcsolódó ré
    /// </summary>
    public partial class CharDef
    {
        private char character;
        private bool[,] pixels;
        /// <summary>
        /// A karakter pixeljeit inicializálja egy beépített Windows betűtípus alapján.
        /// </summary>
        public CharDef(char c) : this(c, CharDefViewModel.BuildCharDefPixels(c, Size)) { }

        /// <summary>
        /// Klónozáshoz használható csak ebben az osztályban látható konstruktor.
        /// </summary>
        private CharDef(char c, bool[,] pixels)
        {
            character = c;
            this.pixels = pixels;
        }

        /// <summary>
        /// A karakter, melynek pixeljeit a CharDef tartalmazza.
        /// </summary>
        public char Character { get { return character; } }

        /// <summary>
        /// A karakterek mérete.
        /// Egyszerűsítés: minden betű 15*20 pixeles.
        /// </summary>
        public static Size Size { get; } = new Size(15, 20);

        /// <summary>
        /// A betűdefiníció pixeljeit tartalmazza.
        /// </summary>
        public bool[,] Pixels { get {return pixels; } }

        public CharDef Clone()
        {
            // Klónozzuk a tömböt. Vigyázat, shallow copy-t készít, de mivel itt érték típusú
            // elemek vannak (bool), ezért ez jó nekünk.
            return new CharDef(Character, (bool[,])Pixels.Clone());
        }

        public Memento CreateMemento()
        {
            return new Memento(this);
        }

        public void RestoreFromMemento(Memento m)
        {
            m.GetState(out pixels, out character);
            
        }
    }
}
