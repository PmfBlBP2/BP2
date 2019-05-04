using Microsoft.EntityFrameworkCore;
using MySql.Data.EntityFrameworkCore.Extensions;

namespace HelloConsoleEF
{
  public class ProbaContext : DbContext
  {
    public DbSet<Skola> Skola { get; set; }

    public DbSet<Grad> Grad { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
      optionsBuilder.UseMySQL("server=localhost;port=3306;database=skole-2019;user=root;password=mysqlroot");
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
      base.OnModelCreating(modelBuilder);

      modelBuilder.Entity<Grad>(entity =>
      {
        entity.HasKey(e => e.Id);
        entity.Property(e => e.Naziv).IsRequired();
      });

      modelBuilder.Entity<Skola>(entity =>
      {
        entity.HasKey(e => e.Id);
        entity.Property(e => e.Naziv).IsRequired();
        entity.HasOne(d => d.Grad)
          .WithMany(p => p.Skole);
      });
    }
  }
}