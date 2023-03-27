package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import com.esprit.examen.entities.DetailFacture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplTest {

    @InjectMocks
    ProduitServiceImpl produitService;

    @Mock
    ProduitRepository produitRepository;

    @Mock
    StockRepository stockRepository;

    @Mock
    CategorieProduitRepository categorieProduitRepository;

    @Test
    public void testRetrieveAllProduits() {
        // Given
        Produit produit1 =  new Produit(1L,"code","libelle",12F, new Date(),new Date(),new Stock(), (Set<DetailFacture>) new DetailFacture(),new CategorieProduit());
        Produit produit2 =  new Produit(1L,"code","libelle",12F, new Date(),new Date(),new Stock(), (Set<DetailFacture>) new DetailFacture(),new CategorieProduit());
        List<Produit> produits = new ArrayList<>();
        produits.add(produit1);
        produits.add(produit2);
        when(produitRepository.findAll()).thenReturn(produits);

        // When
        List<Produit> result = produitService.retrieveAllProduits();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(produit1, produit2);
    }

    @Test
    public void testAddProduit() {
        // Given
        Produit produit =  new Produit(1L,"code","libelle",12F, new Date(),new Date(),new Stock(), (Set<DetailFacture>) new DetailFacture(),new CategorieProduit());
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // When
        Produit result = produitService.addProduit(produit);

        // Then
        assertThat(result).isEqualTo(produit);
    }

    @Test
    public void testDeleteProduit() {
        // Given
        Long produitId = 1L;
        doNothing().when(produitRepository).deleteById(produitId);

        // When
        produitService.deleteProduit(produitId);

        // Then
        // No exception should be thrown
    }

    @Test
    public void testUpdateProduit() {
        // Given
        Produit produit =  new Produit(1L,"code","libelle",12F, new Date(),new Date(),new Stock(), (Set<DetailFacture>) new DetailFacture(),new CategorieProduit());
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // When
        Produit result = produitService.updateProduit(produit);

        // Then
        assertThat(result).isEqualTo(produit);
    }


    @Test
    public void testAssignProduitToStock() {
        // Given
        Produit produit = new Produit();
        produit.setLibelleProduit("Produit Test");
        produit.setPrix(100.0F);
        produit.setDateCreation(new Date());
        produit.setIdProduit(1L);
        produit.setCodeProduit("Ref-Test");
        produitService.addProduit(produit);

        Stock stock = new Stock();
        stock.setLibelleStock("Adresse Test");
        stock.setQte(100);
        stockRepository.save(stock);

        // When
        produitService.assignProduitToStock(produit.getIdProduit(), stock.getIdStock());

        // Then
        Produit produitResult = produitService.retrieveProduit(produit.getIdProduit());
        assertEquals(stock.getIdStock(), produitResult.getStock().getIdStock());

        // Clean-up
        produitService.deleteProduit(produit.getIdProduit());
        stockRepository.delete(stock);
    }
}