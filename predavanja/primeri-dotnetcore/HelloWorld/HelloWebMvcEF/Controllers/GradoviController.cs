
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using HelloWebMvcEF.Models;
using HelloWebMvcEF.Data;
using Microsoft.EntityFrameworkCore;

namespace HelloWebMvcEF.Controllers
{
    public class GradoviController : Controller
    {
        private readonly ProbaContext _context;

        public GradoviController(ProbaContext context)
        {
            _context = context;
        }

        public async Task<IActionResult> Index()
        {
            return View(_context.Grad.ToList());
        }

        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var grad = await _context.Grad
                .Include(g => g.Skole)
                .AsNoTracking()
                .FirstOrDefaultAsync(g => g.Id == id);

            if (grad == null)
            {
                return NotFound();
            }

            return View(grad);
        }
    }
}