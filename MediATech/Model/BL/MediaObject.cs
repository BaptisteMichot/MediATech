using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public abstract class MediaObject : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler? PropertyChanged;

        private int _id;
        private string _title;
        private bool _available;
        private string _state;
        private DateOnly _publicationDate;


        public MediaObject(int id, string title, bool available, string state, DateOnly publicationDate)
        {
            Id = id;
            Title = title;
            Available = available;
            State = state;
            PublicationDate = publicationDate;
        }


        public int Id { get => _id; set => _id = value; }

        public string Title
        {
            get => _title;
            set
            {
                _title = value;
                OnPropertyChanged(nameof(Title));
            }
        }

        public bool Available
        {
            get => _available;
            set
            {
                _available = value;
                OnPropertyChanged(nameof(Available));
            }
        }

        public string State
        {
            get => _state;
            set
            {
                _state = value;
                OnPropertyChanged(nameof(State));
            }
        }

        public DateOnly PublicationDate { get => _publicationDate; set => _publicationDate = value; }


        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
