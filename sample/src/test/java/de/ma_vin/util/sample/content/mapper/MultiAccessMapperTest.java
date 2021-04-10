package de.ma_vin.util.sample.content.mapper;

import de.ma_vin.util.sample.content.dao.RootDao;
import de.ma_vin.util.sample.content.dao.multi.MultiRefOneParentDao;
import de.ma_vin.util.sample.content.dao.multi.MultiRefTwoParentsDao;
import de.ma_vin.util.sample.content.domain.Root;
import de.ma_vin.util.sample.content.domain.multi.MultiRefOneParent;
import de.ma_vin.util.sample.content.domain.multi.MultiRefTwoParents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.ma_vin.util.sample.content.ObjectFactory.*;
import static de.ma_vin.util.sample.content.ObjectFactory.getNextId;
import static org.junit.jupiter.api.Assertions.*;

public class MultiAccessMapperTest {
    private RootDao rootDao;
    private MultiRefOneParentDao multiRefOneParentDao;
    private MultiRefTwoParentsDao multiRefTwoParentsDao;

    private Root root;
    private MultiRefOneParent multiRefOneParent;
    private MultiRefTwoParents multiRefTwoParents;

    @BeforeEach
    public void setUp() {
        initObjectFactory();
        multiRefOneParentDao = createMultiRefOneParentDaoWithChildren(getNextId());
        multiRefTwoParentsDao = createMultiRefTwoParentsDao(getNextId());
        rootDao = createRootDao(getNextId());

        initObjectFactory();
        multiRefOneParent = createMultiRefOneParentWithChildren(getNextId());
        multiRefTwoParents = createMultiRefTwoParents(getNextId());
        root = createRoot(getNextId());
    }

    @Test
    public void testConvertToMultiRefOneParent() {
        MultiRefOneParent result = MultiAccessMapper.convertToMultiRefOneParent(multiRefOneParentDao, false);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefOneParentDao.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefOneParentDao.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(0, result.getMultiRefs().size(), "Wrong number of MultiRefs");
    }

    @Test
    public void testConvertToMultiRefOneParentWithChildren() {
        MultiRefOneParent result = MultiAccessMapper.convertToMultiRefOneParent(multiRefOneParentDao, true);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefOneParentDao.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefOneParentDao.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(multiRefOneParentDao.getMultiRefs().size(), result.getMultiRefs().size(), "Wrong number of MultiRefs");
    }

    @Test
    public void testConvertToMultiRefOneParentWithParent() {
        MultiRefOneParent result = MultiAccessMapper.convertToMultiRefOneParent(multiRefOneParentDao, false, root);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefOneParentDao.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefOneParentDao.getDescription(), result.getDescription(), "Wrong description");

        assertTrue(root.getMultiRefs().contains(result), "Wrong multi ref at root");
    }

    @Test
    public void testConvertToMultiRefOneParentNull() {
        assertNull(MultiAccessMapper.convertToMultiRefOneParent(null, false), "The result should be null");
    }

    @Test
    public void testConvertToMultiRefTwoParents() {
        MultiRefTwoParents result = MultiAccessMapper.convertToMultiRefTwoParents(multiRefTwoParentsDao);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefTwoParentsDao.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefTwoParentsDao.getDescription(), result.getDescription(), "Wrong description");
    }

    @Test
    public void testConvertToMultiRefTwoParentsWithRootParent() {
        MultiRefTwoParents result = MultiAccessMapper.convertToMultiRefTwoParents(multiRefTwoParentsDao, root);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefTwoParentsDao.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefTwoParentsDao.getDescription(), result.getDescription(), "Wrong description");

        assertTrue(root.getAnotherMultiRefs().contains(result), "Wrong multi ref at root");
    }

    @Test
    public void testConvertToMultiRefTwoParentsWithOtherParent() {
        MultiRefTwoParents result = MultiAccessMapper.convertToMultiRefTwoParents(multiRefTwoParentsDao, multiRefOneParent);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefTwoParentsDao.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefTwoParentsDao.getDescription(), result.getDescription(), "Wrong description");

        assertTrue(multiRefOneParent.getMultiRefs().contains(result), "Wrong multi ref at other parent");
    }

    @Test
    public void testConvertToMultiRefTwoParentsNull() {
        assertNull(MultiAccessMapper.convertToMultiRefTwoParents(null), "The result should be null");
    }

    @Test
    public void testConvertToMultiRefOneParentDao() {
        MultiRefOneParentDao result = MultiAccessMapper.convertToMultiRefOneParentDao(multiRefOneParent, false);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefOneParent.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefOneParent.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(0, result.getMultiRefs().size(), "Wrong number of MultiRefs");
    }

    @Test
    public void testConvertToMultiRefOneParentDaoWithChildren() {
        MultiRefOneParentDao result = MultiAccessMapper.convertToMultiRefOneParentDao(multiRefOneParent, true);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefOneParent.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefOneParent.getDescription(), result.getDescription(), "Wrong description");

        assertEquals(multiRefOneParent.getMultiRefs().size(), result.getMultiRefs().size(), "Wrong number of MultiRefs");
    }

    @Test
    public void testConvertToMultiRefOneParentDaoWithParent() {
        MultiRefOneParentDao result = MultiAccessMapper.convertToMultiRefOneParentDao(multiRefOneParent, false, rootDao);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefOneParent.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefOneParent.getDescription(), result.getDescription(), "Wrong description");

        assertTrue(rootDao.getMultiRefs().contains(result), "Wrong multi ref at root");
    }

    @Test
    public void testConvertToMultiRefOneParentDaoNull() {
        assertNull(MultiAccessMapper.convertToMultiRefOneParentDao(null, false), "The result should be null");
    }

    @Test
    public void testConvertToMultiRefTwoParentsDao() {
        MultiRefTwoParentsDao result = MultiAccessMapper.convertToMultiRefTwoParentsDao(multiRefTwoParents);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefTwoParents.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefTwoParents.getDescription(), result.getDescription(), "Wrong description");
    }

    @Test
    public void testConvertToMultiRefTwoParentsDaoWithRootParent() {
        MultiRefTwoParentsDao result = MultiAccessMapper.convertToMultiRefTwoParentsDao(multiRefTwoParents, rootDao);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefTwoParents.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefTwoParents.getDescription(), result.getDescription(), "Wrong description");

        assertTrue(rootDao.getAnotherMultiRefs().contains(result), "Wrong multi ref at root");
    }

    @Test
    public void testConvertToMultiRefTwoParentsDaoWithOtherParent() {
        MultiRefTwoParentsDao result = MultiAccessMapper.convertToMultiRefTwoParentsDao(multiRefTwoParents, multiRefOneParentDao);
        assertNotNull(result, "There should be any result");
        assertEquals(multiRefTwoParents.getIdentification(), result.getIdentification(), "Wrong identification");
        assertEquals(multiRefTwoParents.getDescription(), result.getDescription(), "Wrong description");

        assertTrue(multiRefOneParentDao.getMultiRefs().contains(result), "Wrong multi ref at other parent");
    }

    @Test
    public void testConvertToMultiRefTwoParentsDaoNull() {
        assertNull(MultiAccessMapper.convertToMultiRefTwoParentsDao(null), "The result should be null");
    }

    @Test
    public void testGetInstance() {
        MultiAccessMapper firstInstance = MultiAccessMapper.getInstance();
        assertNotNull(firstInstance, "An instance should be created");
        assertSame(firstInstance, MultiAccessMapper.getInstance(), "Any other instance should be the same");
    }
}