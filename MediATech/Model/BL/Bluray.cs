using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public class Bluray : MediaObject
    {
        private bool _is4K;
        private int _duration;


        public Bluray(int id, string title, bool available, string state, DateOnly publicationDate, bool is4K, int duration)
            : base(id, title, available, state, publicationDate)
        {

        }


        public bool Is4K { get => _is4K; set => _is4K = value; }

        public int Duration { get => _duration; set => _duration = value; }
    }
}
