using System.Diagnostics;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using WebAuthentication.Chiphers.CaesarCipher;
using WebAuthentication.Chiphers.Viginere;
using WebAuthentication.Models;

namespace WebAuthentication.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;

    public HomeController(ILogger<HomeController> logger)
    {
        _logger = logger;
    }

    [Authorize]
    public IActionResult Index()
    {
        return View();
    }
    
    [Authorize]
    public IActionResult CaesarView()
    {
        return View();
    }
    
    [Authorize]
    public IActionResult ViginereView()
    {
        return View();
    }
    
    [Authorize]
    [HttpPost]
    public IActionResult CaesarCipher(string textToEncrypt, int key)
    {
        var model = new CaesarModel
        {
            InitialText = textToEncrypt,
            EncryptedResult = Caesar.Encrypt(textToEncrypt, key),
            DecryptedResult = Caesar.Decrypt(textToEncrypt, key)
        };
        
        return View(model);
    }
    
    [Authorize]
    [HttpPost]
    public IActionResult ViginereCipher(string textToEncrypt, string key)
    {
        var model = new ViginereModel
        {
            InitialText = textToEncrypt,
            EncryptedResult = Viginere.Encrypt(textToEncrypt, key),
            DecryptedResult = Viginere.Decrypt(textToEncrypt, key),
        };
        
        return View(model);
    }

    public IActionResult Privacy()
    {
        return View();
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }

   
}