<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use AppBundle\Entity\Reservations;

class MainController extends Controller
{
    /**
     * @Route("/", name="homepage")
     */
    public function indexAction() {
        return $this->render('appbundle/index.html.twig', array(          
        ));
    }
    
    public function loadNewDocumentsAction(Request $request)  {  
        
        $em = $this->getdoctrine()->getManager();
        $documentTypesList = $em->getRepository('AppBundle:DocumentType')->findAll();   //Va chercher en BDD la liste des types de documents pour les envoyer dans twig
        $typeId =  $request->get('typeId');                                                                            //Réccupère la valeur de "duration" dans l'URL
        $durationValue = $request->get('duration');       
        
        //Si "durationValue" n'est pas définit => Renvoie tous les documents avec une valeur de "durationValue fixée à 45)
         if (!$typeId) {
             if (!$durationValue) {                
            $durationValue = 12545;             //A modifier pour couvrir lus de 45 jours (modification nécéssaire dans toutes les requêtes
            }
            $newBooks = $em->getRepository('AppBundle:Books')->findAllNewBooks($durationValue);
            $newCDs = $em->getRepository('AppBundle:Cds')->findAllNewCDs($durationValue);
            $newDVDs = $em->getRepository('AppBundle:Dvds')->findAllNewDVDs($durationValue);
            $newComics = $em->getRepository('AppBundle:Comics')->findAllNewComics($durationValue);
            
            return $this->render('appbundle/newDocuments.html.twig', array(
            'newBooks' => $newBooks,
            'newCDs' => $newCDs,
            'newDVDs' => $newDVDs,
            'newComics' => $newComics,
            'documentTypesList' => $documentTypesList,
            'durationValue' => $durationValue,
            'typeId' => $typeId,
        ));
        } else {        //Renvoie tous les documents récents selon la valeur de "durationValue & type de documents."      
               
                $newCDs = $em->getRepository('AppBundle:Cds')->findNewCDs($durationValue, $typeId);
                $newDVDs = $em->getRepository('AppBundle:Dvds')->findNewDVDs($durationValue, $typeId);
                $newBooks = $em->getRepository('AppBundle:Books')->findNewBooks($durationValue, $typeId);
                $newComics = $em->getRepository('AppBundle:Comics')->findNewComics($durationValue, $typeId);
                
        return $this->render('appbundle/newDocuments.html.twig', array(
            'newBooks' => $newBooks,
            'newCDs' => $newCDs,
            'newDVDs' => $newDVDs,
            'newComics' => $newComics,
            'documentTypesList' => $documentTypesList,
            'durationValue' => $durationValue,
            'typeId' => $typeId,
        ));
        }
        
    }
    
     public function loadLocalEventsAction(Request $request) {
         $em = $this->getdoctrine()->getManager();
         $localEvents = $em->getRepository('AppBundle:Events')->findAll();
         
         return $this->render('appbundle/localEvents.html.twig', array (
            'localEvents' =>  $localEvents
         ));
         
     }
     public function documentReservationAction (Request $request) {
        $todayDate = new \DateTime();
        $em = $this->getdoctrine()->getManager();
        $documentId = $request->get('documentId');
        $userId = $request->get('userId');
        $documentObject = $em->getRepository('AppBundle:Documents')->findOneBy(array(
            'id' =>$documentId
                ));
        $userObject = $em->getRepository('AppBundle:User')->findOneBy(array(
            'id' =>$userId
                ));
        $isBooked = $em->getRepository('AppBundle:Reservations')->findOneBy(array(
            'document' => $documentId,
                ));
        
        if (!$isBooked) {
       
        $newReservation = new Reservations();
        $documentUp = $em->getRepository('AppBundle:Documents')->findOneBy(array(                           //maj table document pour indiquer qu'il es reservé
             'id' =>$documentId
             ));
        $newReservation->setReservationDate($todayDate);
        $newReservation->setUser($userObject);
        $newReservation->setDocument($documentObject);
        $documentUp->setBooked('yes');
        
        $em->persist($newReservation);
        
        $em->flush();        
        
        return $this->render('appbundle/bookingValidation.html.twig', array(
            'isBooked' => $isBooked,
            ));
        } else {
     
        return $this->render('appbundle/bookingValidation.html.twig', array(
            'isBooked' => $isBooked,
            ));
        }
        
     }
     
     public function getLocalEventsAction() {
        
          $em = $this->getdoctrine()->getManager();
          $localEvents = $em->getRepository('AppBundle:Events')->findAll();         
          
          if ($localEvents) {
                //Sérialise la liste d'objets "évènement" contenu dans la variable $localEvents (Pense aux Uses => "ObjectNormalizer", "Sérializer" et "JsonEncodeur" en tête du doc"
                $normalizer = new ObjectNormalizer();
                $serializer = new Serializer([$normalizer], [new JsonEncoder()]);
                $json = $serializer->serialize( $localEvents, 'json');              
              
                //Retourne la réponse en JSON (penser au Use => "Response" en tête du doc
                $response = new Response($json);
                $response->headers->set('Content-Type', 'application/json');
                return $response;
              
          } 
          
     }
     
}
