<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class LoadOtherPagesController extends Controller
{
    
     public function sendOtherPagesAction (Request $request)  {
        $pageValue = $request->get('pageValue');
            if ($pageValue == 0) {        
            return $this->render('appbundle/index.html.twig');
            }
            elseif ($pageValue == 1) {        
            return $this->render('otherPages/cadreDeViePage.html.twig');
            }
            elseif ($pageValue == 2) {        
            return $this->render('otherPages/vieMunicipale.html.twig');
            }
            elseif ($pageValue == 3) {        
            return $this->render('otherPages/vieLocaleEtSociale.html.twig');
            }
            elseif ($pageValue ==4) {        
            return $this->render('otherPages/vieAssociativeEtCulturelle.html.twig');
            }
            elseif ($pageValue == 5) {        
            return $this->render('otherPages/liens.html.twig');
            }
            elseif ($pageValue == 6) {        
            return $this->render('otherPages/economie.html.twig');
            }        
    }     
}
