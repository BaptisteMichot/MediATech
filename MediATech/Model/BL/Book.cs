using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public class Book : MediaObject
    {
        private string _isbn;
        private string _author;
        private string _publisher;
        private int _pageCount;
        

        public Book(int id, string title, bool available, string state, DateOnly publicationDate, string isbn, string author, string publisher, int pageCount)
            : base(id, title, available, state, publicationDate)
        {
            ISBN = isbn;
            Author = author;
            Publisher = publisher;
            PageCount = pageCount;
        }


        public string ISBN { get => _isbn; set => _isbn = value; }

        public string Author { get =>  _author; set => _author = value;}

        public string Publisher { get => _publisher; set => _publisher = value;}

        public int PageCount { get => _pageCount; set => _pageCount = value;}
    }
}
