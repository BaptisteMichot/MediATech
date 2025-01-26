using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace MediATech.Model.BL
{
    public class Fine : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler? PropertyChanged;

        private int _fineId;
        private double _fineRate;
        private DateOnly _issueDate;

        private int _overdueDays;
        private double _amount;

        public Fine(int fineId, double fineRate, DateOnly issueDate)
        {
            FineId = fineId;
            FineRate = fineRate;
            IssueDate = issueDate;
        }


        public int FineId { get => _fineId; set => _fineId = value; }

        public double FineRate
        {
            get => _fineRate;
            set
            {
                _fineRate = value;
                OnPropertyChanged(nameof(FineRate));
            }
        }

        DateOnly IssueDate { get => _issueDate; set => _issueDate = value; }

        public int OverdueDays { get => _overdueDays; set => _overdueDays = value; }

        public double Amount { get => _amount; set => _amount = value; }


        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
