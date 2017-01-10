<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use AppBundle\Entity\Borrowers;

class ManagerController extends Controller
{
    public function loadManagerPageAction() {
            return $this->render('appbundle/managerPage.html.twig', array(          
            ));
    }
    
    public function getReservationsAction() {      
          $em = $this->getdoctrine()->getManager();
          $reservations = $em->getRepository('AppBundle:Reservations')->findAll();         
         
                //Sérialise la liste d'objets "évènement" contenu dans la variable $localEvents (Pense aux Uses => "ObjectNormalizer", "Sérializer" et "JsonEncodeur" en tête du doc"
                $normalizer = new ObjectNormalizer();
                $serializer = new Serializer([$normalizer], [new JsonEncoder()]);
                $json = $serializer->serialize( $reservations, 'json');              
              
                //Retourne la réponse en JSON (penser au Use => "Response" en tête du doc
                $Response = new Response($json);
                $Response->headers->set('Content-Type', 'application/json');
                return $Response;      
     }
     
     public function validReservationAction (Request $request) {
        $em = $this->getdoctrine()->getManager();
        $documentId = $request->get('document_id');
        $userId = $request->get('user_id');
        $documentObject = $em->getRepository('AppBundle:Documents')->find($documentId);
        $userObject = $em->getRepository('AppBundle:User')->find($userId);
        $reservationObject = $em->getRepository('AppBundle:Reservations')->findOneBy(array(
            'document'=>$documentId
                ));
        $todayDate = new \DateTime();       
             
        $TodayDateForReturnDate = new \DateTime();                                                          //Obligation de déclarer à nouveua un ew DateTime pour réaliser l'addition dessus.
        $intervalDate = new \DateInterval('P15D'); 
        $returnDate =  $TodayDateForReturnDate->add($intervalDate);
        
        $newBorrowing = new Borrowers();
        
        $newBorrowing->setUser($userObject);
        $newBorrowing->setDocument($documentObject);
        $newBorrowing->setBorrowDate($todayDate);
        $newBorrowing->setReturnDate($returnDate);
         
        $em->persist($newBorrowing);
        $em->remove($reservationObject);
        $em->flush();        
        
        return $this->render('appbundle/validBorrowingPage.html.twig', array(
            'userId' => $userObject,           
            ));            
        }
        
        public function loadBorrowersAction (){
             $em = $this->getdoctrine()->getManager();
             $borrowers = $em->getRepository('AppBundle:Borrowers')->findAll();
             
             return $this->render('appbundle/borrowersList.html.twig', array(
            'borrowers' => $borrowers,           
            ));
        }
        
        public function delBorrowersAction (Request $request){
             $em = $this->getdoctrine()->getManager();
             $documentId = $request->get('documentId');
             $borrowerObject = $em->getRepository('AppBundle:Borrowers')->findOneBy(array(
                 'document'=>$documentId
                     ));
             $documentUp = $em->getRepository('AppBundle:Documents')->findOneBy(array(                           //maj table document pour indiquer qu'il n'est plus reservé
             'id' =>$documentId
             ));             
             $documentUp->setBooked('no');
             $em->remove($borrowerObject);
             $em->flush();
             
              return $this->render('appbundle/validBorrowingReturn.html.twig', array(
               
            ));
        }
}

