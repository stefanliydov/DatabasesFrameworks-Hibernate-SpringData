package app.exam.service.impl;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(dontRollbackOn = IllegalArgumentException.class)
public class ItemsServiceImpl implements ItemsService {

    private ItemsRepository itemsRepository;
    private ModelParser modelParser;
    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository, ModelParser modelParser, CategoryRepository categoryRepository) {
        this.itemsRepository = itemsRepository;
        this.modelParser = modelParser;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(ItemJSONImportDTO dto) {
        if(this.itemsRepository.findByName(dto.getName()) != null){
            throw new IllegalArgumentException("Item already in database!");
        }
        Category category = categoryRepository.findOneByName(dto.getCategory());
        if(category == null){
            category = new Category();
            category.setName(dto.getName());
        }
        this.categoryRepository.save(category);

        Item item = modelParser.convert(dto,Item.class);
        item.setCategory(category);
        this.itemsRepository.saveAndFlush(item);

    }
}
