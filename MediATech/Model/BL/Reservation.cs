using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public class Reservation : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler? PropertyChanged;

        private int _reservationId;
        private DateOnly _reservationDate;
        private DateOnly _expirationDate;
        private bool _active;


        public Reservation(int reservationId, DateOnly reservationDate, DateOnly expirationDate, bool active)
        {
            ReservationId = reservationId;
            ReservationDate = reservationDate;
            ExpirationDate = expirationDate;
            Active = active;
        }


        public int ReservationId { get => _reservationId; set => _reservationId = value; }

        public DateOnly ReservationDate { get => _reservationDate; set => _reservationDate = value; }

        public DateOnly ExpirationDate { get => _expirationDate; set => _expirationDate = value; }

        public bool Active
        {
            get => _active;
            set
            {
                _active = value;
                OnPropertyChanged(nameof(Active));
            }
        }


        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
