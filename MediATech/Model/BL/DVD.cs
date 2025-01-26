using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public class DVD : MediaObject
    {
        private int _duration;


        public DVD(int id, string title, bool available, string state, DateOnly publicationDate, int duration)
            : base(id, title, available, state, publicationDate)
        {
            Duration = duration;
        }


        public int Duration { get =>  _duration; set => _duration = value;}
    }
}
