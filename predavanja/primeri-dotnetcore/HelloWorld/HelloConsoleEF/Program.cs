using Microsoft.EntityFrameworkCore;
using System;
using System.Text;

namespace HelloConsoleEF
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Prikaz podataka iz baze:");
            PrintData();
            Console.Read();
        }

        private static void PrintData()
        {
            // Gets and prints all books in database
            using (var context = new ProbaContext())
            {
                var skole = context.Skola.Include(p => p.Grad);
                foreach (var s in skole)
                {
                    var data = new StringBuilder();
                    data.AppendLine($"Naziv: {s.Naziv}");
                    data.AppendLine($"Adresa: {s.Adresa}");
                    data.AppendLine($"Grad: {s.Grad.Naziv}");
                    Console.WriteLine(data.ToString());
                }
            }

        }
    }
}
