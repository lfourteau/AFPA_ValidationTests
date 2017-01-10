<?php

namespace AppBundle\Repository;

use Doctrine\ORM\EntityRepository;

class ComicsRepository extends EntityRepository {
    function findAllNewComics ($durationValue) {
            $todayDate = new \DateTime();
            $dateInterval =new \DateInterval("P".$durationValue."D");
            $dateXDaysAgo = $todayDate->sub($dateInterval);           
        
            $qb = $this->createQueryBuilder('b');
            $qb   ->innerjoin('b.document', 'd')
                    ->innerjoin('d.documenttype', 'dtype')
                    ->where ('d.arrivalDate >= :dateXDaysAgo')                   
                    ->setParameter ('dateXDaysAgo', $dateXDaysAgo);
                    
            
            return $qb
                ->getQuery()
                ->getResult();
    }
    
    function findNewComics ($durationValue, $typeId) {
            $todayDate = new \DateTime();
            $dateInterval =new \DateInterval("P".$durationValue."D");
            $dateXDaysAgo = $todayDate->sub($dateInterval);           
        
            $qb = $this->createQueryBuilder('b');
            $qb   ->innerjoin('b.document', 'd')
                    ->innerjoin('d.documenttype', 'dtype')
                    ->where ('d.arrivalDate >= :dateXDaysAgo')
                    ->andWhere('d.documenttype = :typeId')
                    ->setParameter ('dateXDaysAgo', $dateXDaysAgo)
                    ->setParameter ('typeId', $typeId);
            
            return $qb
                ->getQuery()
                ->getResult();
    }
}
